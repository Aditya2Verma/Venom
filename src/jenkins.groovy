pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                echo 'Installing dependencies...'
                echo 'Building the React application...'
                git branch: 'main', url: 'https://github.com/Aditya2Verma/Venom.git'
            }
        }
        stage('Unit Tests') {
            steps {
                echo 'Running unit tests...'
            }
        }
        stage('Code Analysis') {
            steps {
                echo 'No specific code analysis step for this project.'
                // You can integrate linting or other code analysis tools here if needed
            }
        }
        stage('Deploy to Staging') {
            steps {
                echo 'No specific staging deployment step for this project.'
                // You can add deployment to staging environment if needed
            }
        }
        stage('Integration Tests on Staging') {
            steps {
                echo 'No specific staging integration tests step for this project.'
                // You can add integration tests on staging environment if needed
            }
        }
        stage('Deploy to Production') {
            steps {
                echo 'No specific production deployment step for this project.'
                // You can add deployment to production environment if needed
            }
            
            post {
                success {
                    script {
                        def powershellCommand = """
                            \$SMTPServer = "smtp.gmail.com"
                            \$SMTPFrom = "aditya4754.be22@chitkara.edu.in"
                            \$SMTPTo = "aditya4754.be22@chitkara.edu.in"
                            \$SMTPSubject = "GREAT SUCCESS "
                            \$SMTPBody = "Pipeline is executed successfully"
                            \$SMTPUsername = "aditya4754.be22@chitkara.edu.in"
                            \$SMTPPassword = "nady ofrb ipgh uvmj"
            
                            Send-MailMessage -From \$SMTPFrom -to \$SMTPTo -Subject \$SMTPSubject -Body \$SMTPBody -SmtpServer \$SMTPServer -UseSsl -Port 587 -Credential (New-Object System.Management.Automation.PSCredential \$SMTPUsername, (ConvertTo-SecureString -AsPlainText \$SMTPPassword -Force))
                        """
                        powershell(powershellCommand)
                    }
                    
                    echo 'GREAT SUCCESS!'
                }
                failure {
                    script {
                        def powershellCommand = """
                            \$SMTPServer = "smtp.gmail.com"
                            \$SMTPFrom = "aditya4754.be22@chitkara.edu.in"
                            \$SMTPTo = "aditya4754.be22@chitkara.edu.in"
                            \$SMTPSubject = "FAILURE"
                            \$SMTPBody = "Pipeline failed to execute, errors occured"
                            \$SMTPUsername = "aditya4754.be22@chitkara.edu.in"
                            \$SMTPPassword = "nady ofrb ipgh uvmj"
            
                            Send-MailMessage -From \$SMTPFrom -to \$SMTPTo -Subject \$SMTPSubject -Body \$SMTPBody -SmtpServer \$SMTPServer -UseSsl -Port 587 -Credential (New-Object System.Management.Automation.PSCredential \$SMTPUsername, (ConvertTo-SecureString -AsPlainText \$SMTPPassword -Force))
                        """
                        powershell(powershellCommand)
                    }
                    echo 'Build FAILED, CHECK.'
                }
            }
        }
    }
}
