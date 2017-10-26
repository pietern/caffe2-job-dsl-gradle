job("caffe2-pull-request") {
  scm {
    git {
      remote {
        github('pietern/caffe2')
        refspec('+refs/pull/*:refs/remotes/origin/pr/*')
      }
      branch('${sha1}')
    }
  }

  triggers {
    githubPullRequest {
    }
  }
}
