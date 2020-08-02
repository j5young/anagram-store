#! /bin/bash

npm install -g serverless
serverless deploy --stage dev --aws-profile devProfile --package $CODEBUILD_SRC_DIR/target/dev -v