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
        sh 'newman run ./Postman/Challenge Backend.postman_collection.json'
      }
    }
  }
  post {
    success {
      echo 'The Pipeline success.'
      sh 'docker rm newman'
    }
    failure {
      script {
        echo 'The Pipeline failed.'
        sh 'docker rm newman'
      }
    }
  }
}
