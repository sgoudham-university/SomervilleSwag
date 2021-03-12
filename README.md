# SomervilleSwag

## Authors
Goudham Suresh, Alan Robinson, Erin Heath, Hazel Fulton, Robert Ranson, Steven Ferguson

![version-shield](https://img.shields.io/badge/version-v1.0.SNAPSHOT-informational)
![project-type-shield](https://img.shields.io/badge/project%20type-group_project-blueviolet)
![last-commit-shield](https://img.shields.io/github/last-commit/sgoudham-university/SomervilleSwag)
![issues-shield](https://img.shields.io/github/issues/sgoudham-university/SomervilleSwag?label=issues)

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
- Amend Customer Details  
- Browse Products
- Add & Remove Products To/From Cart
- Purchase Somerville Goodies

## Architecture

![Architecture Diagram](https://i.imgur.com/B9jrXLq.png)

- **Data**
  - SQLite Database Used
- **Engine**
  - Design Patterns Used - Singleton, State, Adapter
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
     
     - Adapters are used here to stub out classes in order to achieve more test coverage and enforce the Single Responsibility principle, 
     in which functions should only perform one action.
  
  3. JFrames/JPanels will be used for the display, allowing users to interact with the application with ease. 
    
- **Challenges**
  
  1. Implementing State Design Patterns is quite difficult when dealing with asynchronous event listeners of the JFrames/JPanels

### Class Diagrams

PlaceHolder

### Retrospective Actions

PlaceHolder

