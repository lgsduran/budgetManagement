pipeline {
  agent {
    docker {
     image 'postman/newman'
     args '--entrypoint='
    }
  }
  stages {
    stage('Test API') {
      steps {
        sh 'newman --version'
        sh 'newman -u https://www.getpostman.com/collections/658854deae123990fbae'
      }
    }
  }
  post {
    success {
      echo 'The Pipeline success.'
      sh 'docker image rm'
    }
    failure {
      script {
        echo 'The Pipeline failed.'
        sh 'docker image rm'
      }
    }
  }
}
