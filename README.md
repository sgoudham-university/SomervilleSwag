<p align="center">
  <img src="https://i.imgur.com/0B8qdVN.png" alt="SomervilleSwag">
</p>

<p align="center">
  <img src="https://img.shields.io/badge/version-0.6.2-informational" alt="version-shield">
  <img src="https://img.shields.io/badge/project%20type-group_project-blueviolet" alt="project-type-shield">
  <img src="https://img.shields.io/github/contributors/sgoudham-university/SomervilleSwag" alt="contributors-shield">
  <img src="https://img.shields.io/github/issues/sgoudham-university/SomervilleSwag?label=issues" alt="issues-shield">
  <img src="https://img.shields.io/github/issues-pr/sgoudham-university/SomervilleSwag?label=pull%20requests" alt="pull-requests-shield">
  <img src="https://img.shields.io/github/last-commit/sgoudham-university/SomervilleSwag" alt="last-commit-shield">
  <img src="https://travis-ci.com/sgoudham-university/SomervilleSwag.svg?branch=main" alt="travisci-build-shield">
  <a href="https://codecov.io/gh/sgoudham-university/SomervilleSwag">
    <img src="https://codecov.io/gh/sgoudham-university/SomervilleSwag/branch/main/graph/badge.svg?token=ZucNh1lSYR" alt="codecov-shield"/>
  </a>
</p>

## Authors
Goudham Suresh, Alan Robinson, Erin Heath, Hazel Fulton, Robert Ranson, Steven Ferguson

## Table of Contents

- [Project Description](#Project-Purpose-&-Description)
- [App Functionality](#App-Functionality)
- [Architecture](#Architecture)
    - [Benefits & Challenges](#benefits--challenges)
    - [Class Diagrams](#Class-Diagrams)
    - [Retrospective Actions](#Retrospective-Actions)

## Project Purpose & Description

- Purpose
  - Allows customers to purchase Derek Somerville Merch.
- Description
  - A standalone GUI Java application for SomervilleSwag, founded by Global Influencer Derek Somerville. Having won many awards from his time working at J.P Morgan, he decided to pursue the Instagram Influencer Lifestyle and founded his own merch store "SomervilleSwag"
  
## App Functionality 
- Sign Up
- Log In
- Browse Products
- Add & Remove Products To/From Cart
- Purchase Somerville Goodies

## Architecture

![Architecture Diagram](https://i.imgur.com/B9jrXLq.png)

- **Data**
  - SQLite Database Used
- **Engine**
  - Design Patterns Used - Singleton, State, Adapter, Builder
- **Display** 
  - JavaX.Swing (JFrames/JPanels)
  - Logging Information Exported to File

### Benefits & Challenges

- **Benefits**
  
  1. SQLite Database is incredibly beneficial as it is a flat-file relational database using SQL. 
     It's incredibly lightweight and blazingly fast, providing speed as well as reliability. 
     Therefore, perfect for our small GUI application that will not store large amounts of data.
     
  2. - Mixture of Singleton/State/Adapter patterns have been used. This adheres to clean coding standards and principles while 
     also making this application maintainable for any future features / bug fixes.
       
     - Singletons allow us to ensure only 1 instance of an object is available throughout the entire program, 
     such as the LoggingService. 
     
     - States allow us to remove lots of conditional logic from the main engine and distribute through classes and methods.
    
     - Builder classes improve the readability of code through providing clear, easy to read methods when instantiating/building new objects.
     
     - Adapters are used here to stub out classes in order to achieve more test coverage and enforce the Single Responsibility principle, 
     in which functions should only perform one action.
  
  3. JFrames/JPanels will be used for the display, allowing users to interact with the application with ease. 
    
- **Challenges**
  
  1. Implementing State Design Patterns is quite difficult when dealing with asynchronous event listeners of the JFrames/JPanels.
    
  2. Unit Testing of the UI is virtually impossible through only common libraries of JUnit5 and Mockito. To work around this, we are only unit testing the core business logic.

  3. Displaying product images within the UI proves to be somewhat difficult 

### Class Diagrams

PlaceHolder

### Retrospective Actions

PlaceHolder

