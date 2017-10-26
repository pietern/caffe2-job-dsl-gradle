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
      admin('pietern')
      orgWhitelist('caffe2')
      cron('H/5 * * * *')
      triggerPhrase('OK to test')
      onlyTriggerPhrase()
      useGitHubHooks()
      permitAll()
      allowMembersOfWhitelistedOrgsAsAdmin()
      extensions {
        commitStatus {
          context('deploy to staging site')
          triggeredStatus('starting deployment to staging site...')
          startedStatus('deploying to staging site...')
          addTestResults(true)
          statusUrl('http://mystatussite.com/prs')
          completedStatus('SUCCESS', 'All is well')
          completedStatus('FAILURE', 'Something went wrong. Investigate!')
          completedStatus('PENDING', 'still in progress...')
          completedStatus('ERROR', 'Something went really wrong. Investigate!')
        }
      }
    }
  }
  steps {
    shell 'echo Hello world'
  }
}
