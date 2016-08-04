ABA Industry bits and bobs
-------------------------------

[![Build Status](https://travis-ci.org/maurerit/abandapart.svg?branch=develop)](https://travis-ci.org/maurerit/abandapart) [![Coverage Status](https://coveralls.io/repos/github/maurerit/abandapart/badge.svg)](https://coveralls.io/github/maurerit/abandapart)

### The Problem ###

Being competitive in a situation where you are reliant on purchasing from the open market comes with many complications.  When is the best time to buy your input materials, when should you sell your products, can you use all the hubs instead of just Jita to procure your inputs.  All of these are questions should be asked but none of them are easy to answer.  In EVE there are also things that should be monitored such as assets of the corp, purchases and sales of the corp and many other moving parts that are hard to combine on a day to day basis due to how the data is presented and what data is missed based on the state of the stuff.

---

# Submodules #

There are now two submodules within this git repository.  If you're unsure how to work with submodules see the [git documentation](https://git-scm.com/book/en/v2/Git-Tools-Submodules) for them.  These will need to build by executing the respective gradlew executables in their folders with an argument of install.  This will build the modules and install their artifacts in your local m2 repository.

---

### Java Migration ###

I spent a lot of time on this readme and the node version of this project.  However, I'm far more comfortable in Java and no one wanted to contribute.  Therefore I'm dumping the node version as it has proved itself useful and implementing this in pure Java.  Got a problem with that?  Too damn bad, I don't see you coding up anything and making it available to the alliance for use.

---

### Status ###

During this migration, this status will be out of date.  I'll do my best to get it up to date as soon as possible but I'm in the mood to code and write some markdown.  Watch this space!

---

### The Concepts ###

Micro services and a message bus.  Plain and simple and very scalable.  Simply put, there will be a lot of little things which when combined make up the whole.  For instance, there is currently one piece that listens to the emdr and relays messages that are interesting to the runner, there is another piece that provides the configurations for the entirety of all the micro services (because together, they're one application), there is a piece that will consume messages from the emdr piece and do something else meaningful with it and publish it's results on the bus.  In the middle is the director... a very small thing that _shouldn't_ crash due to his simplicity but is indeed a single point of failure :(.  With this and the fact that no one component knows about the existence of another component besides messages that contain data that they're interested in will lead to this being a super scalable, very pluggable environment that anyone can use to accomplish their goals.

---

### The Data ###

[Fetching Metadata](DATA.md#FetchingMetadata)

[Salary Config](DATA.md#SalaryConfig)

[Products Of Interest](DATA.md#ProductsOfInterest)

---

### Contributing ###

I recommend forking this repository and submitting merge requests

---

### Getting setup ###

Learn how to use docker:
* [Windows](https://docs.docker.com/windows/) 
* [Mac OS X](https://docs.docker.com/mac/)
* [Linux](https://docs.docker.com/linux/)

Learn some zeromq concepts _(optional)_:
* Read up on [zeromq](http://zeromq.org/)
* Look through examples of [zeromq in node](http://zguide.zeromq.org/js:_start)

#### Environment setup ####
* Install docker for your platform (see above)
* If in linux
  * ```mkdir -p /docker/lucrh/{logs,data,config}```
  * ```cp `pwd`/bus-config/config/config.sample.json /docker/lucrh/config/config.json```
      * Edit to your needs
  * ```cp `pwd`/emdr-filter/productsOfInterest.json /docker/lucrh/config```
      * This will soon change to ```cp `pwd`/bus-config/config/productsOfInterest.sample.json /docker/lucrh/config/productsOfInterest.json```
      * Edit to your needs
* If in windows
  * ```mkdir -p run\config```
  * ```mkdir run\logs```
  * ```mkdir run\data```
  * ssh into the docker-machine ```docker-machine ssh deault```
  * ```mkdir /docker```
      * I don't think this will stick from run to run of the docker machine but I could be wrong.
  * Assuming that docker-machine creation is like that on OS X
      * ```ln -s /Users/<your username>/<path to where you are now>/run /docker/lucrh```
* If in OS X
  * Be thankful that you're a smug bastard
  * Stop being a smug bastard and get to work
  * ```mkdir -p run/{logs,config,data}```
  * ssh into the docker-machine ```docker-machine ssh default```
  * ```mkdir /docker```
      * I don't think this will stick from run to run of the docker machine but I could be wrong.
  * ```ln -s /Users/<your username>/<path to where you are now>/run /docker/lucrh```

#### Coding setup ####

Just write better code than me!  I really don't care how you write it, just write it but it'd better work ;)!

---

#### Current pieces ####

I apologize for the terrible naming methods... if you have better names... contribute :P.

**industry-director**: **_Status_**: _done_
  * Serves as a broker for all the components
  * This is the only piece that _ALL_ others will know exists and will connect directly to it

**industry-common-bus**: _Common Component_ **_Status_**: _current priority_
  * Provides a mechanism for connecting to the industry-director.
  * Provides a mechanism for registering a callback for messages of interest
  * Provides hooks for cleaning itself up on SIGTERM events and calling the user to do a cleanup as well through a callback

**industry-config**: **_Status_**: _mostly done_
  * maintains the configurations for all the micro services
  * **Will soon contain management methods which will manipulate the in memory store of the config and also persist to the backing disk**

**industry-calc**: **_Status_**: _mostly done_
  * Listens for various events on the bus and provides build stats for products the runner is interested in producing

**gsheets-cost-interface**: **_Status_**: _mostly done_
  * Binding between the internal bus data and some gsheets documents that require updating
  * Consumes data from other sources such as the build-calc-response message and publish them for viewing

**industry-price-fetcher**: **_Status_**: _mostly done_
  * Responds to common.messages.SALES_PRICE_REQUEST with major hub sales prices

**assets-monitor**: **_Status_** _queued_
  * Will poll the eve api for corp assets
  * Will filter based on products of interest.  Ideally with 2 attributes in the status object
      * Products to Market
      * Inputs from Market

---

#### Planned pieces ####

The below pieces are planned and in current priority order.  Once things in the Current pieces section are done, I will move onto these in this order.

**wallet-monitor**:
  * Will poll the eve api for wallet transactions and journal entries and publish recent entries to the bus

**industry-job-monitor**:
  * Will poll the eve api for industry-jobs and publish current state to the bus

**contract-monitor**:
  * Will poll the eve api for contracts and publish current state to the bus

**product-monitor**:
  * Will maintain a current state snapshot of things in flight and things to be sold

**asset-manager**:
  * Will aggregate data from asset-monitor, wallet-monitor, contract-monitor and publish current state and differences to the bus

**crest-price-seed**: *priority is slipping but may still be relevant*
  * Will run at a certain interval and publish prices for all things of interest to the bus
  * This should be multiple components
      * A load balancing router
      * Several workers to tally out requests
      * See [loadbalancingbroker.js](zeromq-samples/loadbalancingbroker.js)
      * This should also use [docker-compose scaling](https://docs.docker.com/compose/reference/scale/)
          * Router should be stagnant at 1
          * Workers should scale up to whatever...

**build-calc-router**:
  * This and the one below will replace the current build-calc
  * Listen for interesting messages from the EMDR or a heart beat from the crest-price-seed
  * Tally's out work to the build-calc-worker processes
  * Will handle logs from all the workers as well

**build-calc-worker**:
  * A scalable worker to take tasks from the build-calc-router
  * Will also connect to the industry-director for reconfigurations and other interesting messages

**web-interface**:
  * This thing will probably grow to be fairly large and will not to a whole lot constructive on the bus.
  * Configuration management
  * Data viewing and manipulation
  * Reports
  * Maybe each of these things gets dropped into their own service and one web-interface to bind them all together is built... who knows!

**purchase-advisor**:
  * Using data from industry-job-monitor, other potential data sources and the asset-manager this module will produce advice on what to purchase and when.

**sales-advisor**:
  * Using data from product-monitor, emdr-filter and other data sources this module will advise on when and where to sell things

**slack-updater**:
  * Will publish stuff to slack... what that stuff is... I don't know

**logger-writer**:
  * Will listen for logging messages from the industry-director and write them to a log file or do something else entirely.

**logger**: _Common Component_
  * Will be a common component and will self connect to the industry-director
  * Provide wrapper functions for log severity messages.

**config-manager**:
  * Will be a command line interface which will connect directly to the bus-config
  * Will allow the user to change any configuration in the bus-config module
  * Will have mechanisms to import json files to be sent

**health-monitor**:
  * Each module should reply to a ping request
  * This module will send the pings
  * This module will check a configurable array of services to be sure they're up and running
  * Will send a message to the director when something doesn't reply
  * Will notify via slack directly itself if the director is not up.

**price-history-monitor**:
  * Will listen for BUILD_CALC_RESPONSE's and store the outputs for a to be determined amount of time
  * Will listen for SALES_PRICES_RESPONSE's and store the outputs for a to be deterined amount of time
  * Will use the products of interest and request for prices for the inputs whenever a BUILD_CALC_RESPONSE is seen
      * Will store these for a to be determined amount of time
  * Will publish a new message which will contain:
      * Output name, typeId, build cost, invention cost and salary (even though its fairly static)
      * Each input with its name, typeId and sell price
      * Each datacore with its name, typeId and sell price

**price-history-gsheet-interface**:
  * Will consume the PRICE_HISTORY_MESSAGE and output to a designated google sheet

**corp-task-manager**:
  * Based on profitability of current products and any produceable inputs this module will adjust or suggest adjustments to member tasks.

**emdr-filter**: **_Status_**: _not that useful when I can just get the data myself from crest_
  * Listens to the emdr
  * Uses the products of interest to filter
    * Currently it has its own copy of this but this will soon be pulled from the buses config provider (bus-config)
  * Publishes the emdr messages to the bus when they're deemed interesting

Any other component that could produce something interesting and make use of something that's being produced is more than welcome.  This model of disconnection should make it super simple for multiple things to be developed in parallel.