pipeline {
  agent {
    docker {
     name: postman/newman
     entrypoint: [""]
    }
  }
  stages {
    stage('Test API') {
      steps {
        sh 'newman --version'
        sh 'newman run ./tests/Restful_Booker_Collection.postman_collection.json'
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
