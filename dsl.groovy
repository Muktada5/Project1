multibranchPipelineJob('DSL') {
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
    configure {
        def triggers = it / triggers
        triggers << 'com.cloudbees.jenkins.plugins.GitHubMultibranchTrigger' {
            spec('')
            overrideUrl('https://github.com/Devopsawsr/Docker.git')
        }
    }
    configure {
        def traits = it / sources / data / 'jenkins.branch.BranchSource' / source / traits
        traits << 'jenkins.plugins.git.traits.BranchDiscoveryTrait' {
            strategyId(3)
        }
    }
    configure {
        def traits = it / sources / data / 'jenkins.branch.BranchSource' / source / traits
        traits << 'jenkins.plugins.git.traits.TagDiscoveryTrait' {
            strategyId(3)
        }
    }
    configure {
        def traits = it / sources / data / 'jenkins.branch.BranchSource' / source / traits
        traits << 'jenkins.scm.impl.trait.WildcardSCMHeadFilterTrait' {
            includes('develop hotfix- dev- cit- sit- uat- pre- prd- release- feature-*')
            excludes('main') // master branch is not exposed to Jenkins builds...
        }
    }
}
