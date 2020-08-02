#! /bin/bash

npm install -g serverless
export AWS_ACCESS_KEY_ID=AKIAVLT2IBIG2VCFDW4C
export AWS_SECRET_ACCESS_KEY=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
serverless deploy --stage dev --package $CODEBUILD_SRC_DIR/target/dev -v