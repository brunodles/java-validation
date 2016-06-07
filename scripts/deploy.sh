#!/bin/bash

git config --global user.email "builds@travis-ci.com"
git config --global user.name "Travis CI"

export TAG=$(git log -1 | grep -Eo 'tag: ([0-9\.]+)' | cut -d' ' -f2)

if [ -n "$TAG" ]; then
    echo -n "$TAG">.version
    git commit -am "Prepare release $TAG"
    git tag $TAG
    git push origin $TAG
    git push origin master
fi
