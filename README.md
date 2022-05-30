# Full Stack Developer Coding Test

Be sure to read **all** of this document carefully, and follow the guidelines within.

## Problem Description

ZSSN (Zombie Survival Social Network). The world as we know it has fallen into an apocalyptic scenario. A laboratory-made virus is transforming human beings and animals into zombies, hungry for fresh flesh.

You, as a zombie resistance member (and the last survivor who knows how to code), was designated to develop a system to keep track of non-infected humans.

## Requirements

You will develop a ***REST API*** (yes, we care about architecture design even in the midst of a zombie apocalypse!), which will store information about the survivors, as well as the resources they own. You will also develop a web app that consumes said API.

In order to accomplish this, the API must fulfill the following use cases:

- **Add survivors to the database**

  A survivor must have a *name*, *age*, *gender* and *last location (latitude, longitude)*.

  A survivor also has an inventory of resources of their own property (which you need to declare when upon the registration of the survivor).

- **Update survivor location**

  A survivor must have the ability to update their last location, storing the new latitude/longitude pair in the base (no need to track locations, just replacing the previous one is enough).

- **Flag survivor as infected**

  In a chaotic situation like that, it's inevitable that a survivor may get contaminated by the virus.  When this happens, we need to flag the survivor as infected.

  An infected survivor cannot trade with others, can't access/manipulate their inventory, nor be listed in the reports (infected people are kinda dead anyway, see the item on reports below).

  **A survivor is marked as infected when at least three other survivors report their contamination.**

  When a survivor is infected, their inventory items become inaccessible (they cannot trade with others).

- **Survivors are awarded points based on the items they own **

  Their belongings must be declared when they are first registered in the system, and cannot be added, removed or traded afterwards.

  The items allowed in the inventory are worth points as listed below:

| Item         | Points   |
|--------------|----------|
| 1 Water      | 4 points |
| 1 Food       | 3 points |
| 1 Medication | 2 points |
| 1 Ammunition | 1 point  |

There should be a way for every survivor to browse every survivor's inventory.

- **Reports**

  The API must offer the following reports:

    1. Percentage of infected survivors.
    1. Percentage of non-infected survivors.
    3. Average amount of each kind of resource by survivor (e.g. 5 waters per survivor)
    4. Points lost because of infected survivor (by taking into account the infected survivor's inventory).

---------------------------------------

## Notes about the API

1. Please use one of the following languages/frameworks for the API: *Java/Kotlin* or *Javascript (Node + Express)* - listed in descending order of desirability. It's also possible to implement a solution using *Ruby (Rails)* or *Elixir (Phoenix)*, but if you want to do so, please notify us in advance.
2. No authentication is needed (it's a zombie apocalypse, no one will try to hack a system while running from a horde of zombies);
4. Don't forget to make at least a minimal documentation of the API endpoints and how to use them;
5. You must write at least some automated tests;

## Notes about the web app

1. Please use one of the following languages/frameworks for the API: *Vue.js*. It's also possible to implement a solution using  or *React*, but if you want to do so, please notify us in advance.
2. The webapp should be usable and clear in its use (we want the survivors to understand how to use it as fast as possible);
3. Due to lack of authentication, the webapp should display a way to select which survivor is using the web app, in order to identify their actions;
4. The web app should use all the methods implemented by the API.

## General notes

3. We still care about proper programming and architecture techniques, you must showcase that you're worthy of surving the zombie apocalypse through the sheer strength of your skills;
7. From the problem description above you can either do a very bare bones solution or add optional features that are not described. Use your time wisely; the absolute optimal solution might take too long to be effective in the apocalypse, so you must come up with the best possible solution that will hold up within the least ammount of time and still be able to showcase your skills in order to prove your worth.
8. Write concise and clear commit messages, splitting your changes in little pieces.

## Q&A

> Where should I send back the result when I'm done?

Fork this repo and send us a pull request when you think you are done. We will note you about deadline directly.

> What if I have a question?

Just get in touch with your interviewer by email.

**Original test written by [Akita](https://t.co/W47ODZTOAc)**
