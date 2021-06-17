# tsuki-tsukuruzo
🌙 月作るぞ #tsuki_tsukuruzo for Twitter App.

[![GitHub release (latest by date)](https://img.shields.io/github/v/release/iamtakagi-net/tsuki-tsukuruzo)](https://github.com/iamtakagi-net/tsuki-tsukuruzo/releases)
[![Deploy](https://github.com/iamtakagi-net/tsuki-tsukuruzo/actions/workflows/deploy.yml/badge.svg)](https://github.com/iamtakagi-net/tsuki-tsukuruzo/actions/workflows/deploy.yml)
[![Check-PR](https://github.com/iamtakagi-net/tsuki-tsukuruzo/actions/workflows/ci.yml/badge.svg)](https://github.com/iamtakagi-net/tsuki-tsukuruzo/actions/workflows/ci.yml)
[![license](https://img.shields.io/github/license/iamtakagi-net/tsuki-tsukuruzo)](https://github.com/iamtakagi-net/tsuki-tsukuruzo/blob/master/LICENSE)
[![issues](https://img.shields.io/github/issues/iamtakagi-net/tsuki-tsukuruzo)](https://github.com/iamtakagi-net/tsuki-tsukuruzo/issues)
[![pull requests](https://img.shields.io/github/issues-pr/iamtakagi-net/tsuki-tsukuruzo)](https://github.com/iamtakagi-net/tsuki-tsukuruzo/pulls)

## Installation
`docker-compose.yml`
```yml
version: '3.8'

services:
    db:
        image: mongo
        restart: always
        volumes:
            - db:/data/db
        environment:
            MONGO_INITDB_ROOT_USERNAME: user
            MONGO_INITDB_ROOT_PASSWORD: pass
            MONGO_INITDB_DATABASE: oyatsu
            
    tsuki-tsukuruzo_kotlin:
        image: iamtakagi/tsuki-tsukuruzo_kotlin:latest
        environment:
            TZ: Asia/Tokyo
            HOST: 0.0.0.0
            PORT: 8005
            BASE_URI: /api
            LOG: INFO
            DB_HOST: db
            DB_PORT: 27017
            DB_USER: user
            DB_PASS: pass
            DB_NAME: tsuki
            TWITTER_CK: xxx
            TWITTER_CS: xxx
            CRON: 0 0 0 ? * MON
        restart: always
        ports:
            - 8005:8005

    tsuki-tsukuruzo_next:
        image: iamtakagi/tsuki-tsukuruzo_next:latest
        environment:
            TZ: Asia/Tokyo
            HOST: 0.0.0.0
            PORT: 3005
        ports:
            - 3005:3005
        restart: always

volumes:
    db:
        driver: local
```

## Image


## LICENSE
iamtakagi-net/tsuki-tsukuruzo is provided under the MIT license.
