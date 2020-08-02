#! /bin/bash

npm install -g serverless
export AWS_PROFILE="devProfile" && export AWS_REGION=us-west-2
serverless deploy --stage dev --package $CODEBUILD_SRC_DIR/target/dev -v