multibranchPipelineJob("This is for shanvi Project") {
    branchSources {
        git {
            id('Devops')
            credentialsId('Bithub')
            remote('https://github.com/Devopsawsr/Docker.git')
            includes('*')  // Include all branches
        }
    }
    orphanedItemStrategy {
        discardOldItems {
            numToKeep(-1)
        }
    }
    configure {
        def triggers = it / triggers
        triggers << 'com.cloudbees.jenkins.plugins.BitBucketMultibranchTrigger' {
            spec ''
            overrideUrl('https://github.com/Devopsawsr/Docker.git')
        }
    }
}
