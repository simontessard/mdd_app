# MDD - Fullstack project

## Description

This project is a social network for developers. It allows you to create an account and share your projects with the community.

## Features
- Create an account
- Log in/out
- Edit your profile
- Subscribe/unsubscribe to topics
- Create a post
- Comment on a post

## Front-end

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 14.1.3.

- Using `@angular/material` (https://material.angular.io/), giving highly customizable components that help design interfaces quickly.

- Using Tailwind (https://tailwindcss.com/) for fast CSS uses.

Before running, install your node_modules :

```bash
npm install
```

Update target URL on proxy.config.json if needed and run :

```bash
ng serve --proxy-config src/proxy.config.json
```


## Back-end

The backend is a Java REST API made with Spring Boot. 
It uses a MySQL database to store the data.

Run the SQL scripts you can find on /resources.

Install dependencies before building the API :

```bash
mvn install
```


