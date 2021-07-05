pipeline
{
agent any
stages{
stage('Build')
{
steps{
bat "mvn clean"
}
}
stage('Deploy'){
steps{
echo 'Deploying the code'
}
}
stage('Test'){
steps{
bat "mvn test"
}
}
stage('Release'){
steps{
echo 'Released succesfully'
}
}
}
}
