# Bank Agents - Challenge

The following exercise is for you to apply the concepts we reviewed together in the java fundamentals session we had this week.

## Context
There is a bank office where there are 3 types of agents:

* Cashier
* Supervisor
* Director

The process to attend a client who wants to perform a bank operation (deposits, withdrawals, resolving customer issues) must be attended by a Cashier in the first instance. So, If there are no available cashiers, then it must be attended by a Supervisor, and if there are no available supervisors, the process must be attended by a Director.

## Deliverables

* Create a UML class diagram to communicate the solution.
* There must be a class called "Dispatcher" which will be in charge of handling clients to be attended, so this class must contain a method called "attend()" to assign a client to the next available agent.
* Dispatcher class must be able to attend 10 concurrent clients.
* The attention time of a client must be random, within a range of 10 to 15 seconds
* Dispatcher class must know how long a process lasted.
* There should be a class (main class) that simulates 10 clients.

## Extra points

* javadoc
* What happens when there are more clients than the pool object capacity?
