#!/bin/bash

sh buildOverlappingRect.sh
if [ -z "$1" ]
 then
  echo "Please enter file name"
else
  time sh overlappingRect.sh $1
  time sh overlappingRect.sh $1
fi
