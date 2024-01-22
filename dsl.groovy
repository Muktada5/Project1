multibranchPipelineJob("DSL") {
    branchSources {
        git {
            id('Devops')
            credentialsId('Github')
            remote('https://github.com/Devopsawsr/Docker.git')
            includes('*')  // Include all branches
        }
    }
    orphanedItemStrategy {
        discardOldItems {
            numToKeep(-1)
        }
    }
}
