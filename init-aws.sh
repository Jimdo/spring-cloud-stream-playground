#!/usr/bin/env bash

export QUEUE_NAME=person
export MAX_RECEIVE_COUNT=1

awslocal sqs create-queue --queue-name "$QUEUE_NAME"_dlq
awslocal sqs create-queue --queue-name "$QUEUE_NAME" --attributes '{"RedrivePolicy":"{\"deadLetterTargetArn\":\"arn:aws:sqs:us-east-1:000000000000:'"$QUEUE_NAME"'_dlq\", \"maxReceiveCount\":'"$MAX_RECEIVE_COUNT"'}"}'

awslocal sqs create-queue --queue-name log