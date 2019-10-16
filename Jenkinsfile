properties properties: [[$class: 'GitLabConnectionProperty', gitLabConnection: 'GitLab Sapo']]

def dockerImageTag = ''
def branchName = env.BRANCH_NAME.replaceAll('[/]', '__') ?: ''
def dockerService = getServiceName()
def slackThread = ''

node('java') {
    try {
        stage('Checkout') {
            checkout scm
            def slackResponse = notify('Build started\n' + getGitMessage())
            slackThread = getSlackThread(slackResponse)
        }

        stage('Build java') {
            configFileProvider([configFile(fileId: 'maven_settings', targetLocation: '/tmp', variable: 'MAVEN_SETTINGS')]) {
                withEnv(["PATH+MAVEN=${tool 'Maven'}/bin","PATH+JAVA=${tool 'Java11'}/jdk-11.0.2/bin","JAVA_HOME=${ tool 'Java11'}/jdk-11.0.2"]) {
                    sh('mvn -s $MAVEN_SETTINGS -Pci-cd clean package')
                }
            }
        }

        def dockerImage
        stage('Build docker') {
            dockerImage = docker.build(dockerService, '.')
        }

        dockerImageTag = getGitCommitHash() + '-' + branchName
        stage('Publish docker') {
            docker.withRegistry('https://hub.bizweb.vn', 'hub-login') {
                dockerImage.push dockerImageTag
            }
        }

        notify('Build success\n' + getGitMessage(), '#00FF00', slackThread)
        echo 'Done'
    } catch (e) {
        notify('Build FAILED\n' + getGitMessage(true), '#FF0000', slackThread)
        throw e
    } finally {
        sendLogFile('Click here to view jenkins\'s log file', slackThread)
        notify('Build completed', '', slackThread, false)
    }
}

node('ubuntu') {
    try {
        switch (branchName) {
            case "dev":
            case "staging":
            case "master":
                stage 'Trigger update config'
                def gitMessage = getGitMessage()
                build job: 'beemart-stack', parameters: [string(name: 'DEPLOYMENT', value: "${branchName}"),string(name: 'IMAGE', value: "${dockerService}"),string(name: 'TAG', value: "${dockerImageTag}"),string(name: 'MESSAGE_PREFIX', value: "${messagePrefix}"),string(name: 'SLACK_THREAD', value: "${slackThread}"),string(name: 'GIT_MESSAGE', value: "${gitMessage}")], wait: false
                notify('Trigger to deploy success', '#00FF00', slackThread, false)
                break
        }
    } catch (e) {
        notify('Trigger to deploy FAILED\n' + getGitMessage(true), '#FF0000', slackThread)
        throw e
    }
}

// define funtions
def getServiceName() {
    def matches = (env.JOB_NAME =~ /([a-zA-Z0-9-_]+)(?:\/[a-zA-Z0-9-_]+)?/)
    return  matches.matches() ? "" + matches.group(1) : ''
}

def getGitCommitHash() {
    return sh(returnStdout: true, script: 'git rev-parse --short HEAD').trim()
}

def getGitMessage(mention = false) {
    return sh(returnStdout: true, script: 'git log -2 --pretty=format:"%h - ' + (mention ? '@' : '') + '%an, %ar : %s"').trim()
}

def getMessagePrefix() {
    return '*[' + env.JOB_NAME + ' #' + env.BUILD_NUMBER + ']* '
}

def notify(message, color = '#0000FF', slackThread = '', broadcast = true) {
    def attachments = java.net.URLEncoder.encode('[{"text": "' + getMessagePrefix() + message + '", "color": "' + color + '"}]', "UTF-8")
    withCredentials([[$class: 'StringBinding', credentialsId: 'sapo-slack-token', variable: 'SLACK_TOKEN']]) {
        return sh(returnStdout: true, script: 'curl -X POST -d "attachments=' + attachments + '&reply_broadcast=' + broadcast + '&thread_ts=' + slackThread + '&link_names=true&channel=mr-jenkins&token=$SLACK_TOKEN" https://slack.com/api/chat.postMessage').trim()
    }
}

def sendLogFile(message, slackThread = '') {
    withCredentials([[$class: 'StringBinding', credentialsId: 'sapo-slack-token', variable: 'SLACK_TOKEN']]) {
        sh 'tail -n 100 $JENKINS_HOME/jobs/' + getServiceName() + '/branches/$BRANCH_NAME/builds/$BUILD_NUMBER/log > $JENKINS_HOME/jobs/' + getServiceName() + '/branches/$BRANCH_NAME/builds/$BUILD_NUMBER/tail100.log'
        return sh(returnStdout: true, script: 'curl -F file=@$JENKINS_HOME/jobs/' + getServiceName() + '/branches/$BRANCH_NAME/builds/$BUILD_NUMBER/tail100.log -F "filename=${BUILD_TAG}.log" -F "initial_comment=' + getMessagePrefix() + message + '" -F "thread_ts=' + slackThread + '" -F "channels=mr-jenkins" -F "token=$SLACK_TOKEN" https://slack.com/api/files.upload').trim()
    }
}

def getSlackThread(response = '') {
    def matches = (response =~ /.+"ts"\:"([^"]+)".+/)
    return matches.matches() ? "" + matches.group(1) : ''
}