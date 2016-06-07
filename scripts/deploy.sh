#!/bin/bash

if [ -n "$EMAIL" ]; then
    git config user.email "$EMAIL"
fi
if [ -n "$PASSWORD" ]; then
    git config user.name "Travis Ci"
fi

export TAG=$(git log -1 | grep -Eo 'tag: ([0-9\.]+)' | cut -d' ' -f2)

if [ -n "$TAG" ]; then
    git remote set-url origin "https://brunodles:${PASSWORD}@github.org/brunodles/java-validation.git"
    echo -n "$TAG">.version
    git commit -am "Prepare release $TAG"
    git tag $TAG
    git push origin $TAG
    git push origin master
fi
