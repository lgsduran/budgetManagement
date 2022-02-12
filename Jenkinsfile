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
        sh '''
             newman --version
             newman run https://www.getpostman.com/collections/baa168a892611f4be7b4
           '''
      }
    }
  }
  post {
    success {
      echo 'The Pipeline success.'
    }
    failure {
      script {
        echo 'The Pipeline failed.'
      }
    }
  }
}
