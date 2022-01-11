

# Branch Test

## Run Locally

to run the application locally:

1. build: `./graldew build`
2. start local server: `./gradlew bootRun`
3. confirm app is running, visit in browser: `http://localhost:8080/`
4. invoke call through app to github: `curl --location --request GET 'http://localhost:8080/github/v1/user/{username}'`

## cut for time

* controller setting response status code based on exception type (not found, over quota both result in 500 right now with un-helpful message)
* duplicate code in github client
* unit tests in DefaultGithubClientSpec
* integration tests for GithubClient (tests that actually call real github)
* service layer between controller and client that performs object conversion
* in-depth testing for edge cases and error scenarios
* null input checking
* actual testing for over-quota, just read in the doco that 403 means over-quota, haven't tested it
* care and precision and attention to detail that I'd normally use (ie inconsistent labeling of username vs userid)

## decisions

* groovy - saves time, easy to use, not too tough for java users to read it
* spring boot - familiar with it
* gradle - more familiar with it than maven
* spock - excellent unit testing framework
* not using an existing gitlab library - limited time, went with what I already knew


