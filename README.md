ABA Industry bits and bobs
==========================

#### Branch Status' ####
`Develop`

[![Build Status](https://travis-ci.org/maurerit/abandapart.svg?branch=develop)](https://travis-ci.org/maurerit/abandapart) [![Coverage Status](https://coveralls.io/repos/github/maurerit/abandapart/badge.svg?branch=develop)](https://coveralls.io/github/maurerit/abandapart?branch=develop)

`Master`

[![Build Status](https://travis-ci.org/maurerit/abandapart.svg?branch=master)](https://travis-ci.org/maurerit/abandapart) [![Coverage Status](https://coveralls.io/repos/github/maurerit/abandapart/badge.svg?branch=master)](https://coveralls.io/github/maurerit/abandapart?branch=master)

### The Problem ###

Being competitive in a situation where you are reliant on purchasing from
the open market comes with many complications.  When is the best time to buy
your input materials, when should you sell your products, can you use all the
hubs instead of just Jita to procure your inputs.  All of these are questions
that should be asked but none of them are easy to answer.  In EVE there are
also things that should be monitored such as assets of the corp, purchases
and sales of the corp and many other moving parts that are hard to combine
on a day to day basis due to how the data is presented and what data is missed
based on the state of the stuff.

---

## Submodules ##

There are now two submodules within this git repository.  If you're unsure
how to work with submodules see the [git documentation](https://git-scm.com/book/en/v2/Git-Tools-Submodules)
for them.  These will need to build by executing the respective gradlew
executables in their folders with an argument of install.  This will build
the modules and install their artifacts in your local m2 repository.

---

### Current Status ###

[Industry Calculator](aba-industry/industry-calculator/README.md)
    Currently contains a simple web service to take a build system and a
    type id or name and spew out lots of information.  This information
    is currently specific to Lucifer's Hammer but can be very easily manipulated
    for other purproses.

---

### Current Priorities ###

Vertical Integration
  1. The ability to specify to the industry-calculator that certain inputs are built in house.
  2. The ability to specify buy or sell costs for inputs that are purchased.
  3. Provide a management interface for managing these configurations.
    - This might be easier to accomplish with a cli client... I don't know, my web skills are garbage :(.

Store Front
  1. Allow certain individuals or groups of individuals to log in and view whats on the market.
  2. Allow for custom orders to be placed by entities that are configured to be allowed in.
  3. Allow only items that are profitable or nearly profitable.
      - If not profitable, check if overheads are the problem, try to remove overheads as much as possible

Build Queue
  1. Provide for the ability for managers/planners to create a corporate build queue

Queue Items Assignment
  1. Ability for managers/planners to assign items from the queue to qualified members

Industry Job link to Store Front
  1. When a customer puts in a buy order create a queue item

