
<style>
h1 { border-bottom: 0.5; }
h2 { border-bottom: 0;}
cite { font-size: 10px}
 </style>

## Table of contents

- [Work habits](#work-habits)
  - [Milestones](#milestones)
  - [Issues](#issues)
    - [Labels](#labels)
  - [Branches](#branches)
  - [Commit messages](#commit-messages)
    - [Co-authored-by](#co-authored-by)
- [Work flow](#work-flow)
    - [Open communication](#open-communication)
- [Code quality](#code-quality)
  - [Testing](#testing)
    - [Testing the logic](#testing-the-logic)
    - [Testing the UI](#testing-the-ui)
  - [Tools used to ensure code quality](#tools-used-to-ensure-code-quality)
    - [Jacoco](#jacoco)
    - [Spotbug](#spotbug)
    - [Checkstyle](#checkstyle)

# Work habits

Here are some of our work habits and `git` tools and practices we are following:

## Milestones

In our ongoing work, we start by collaboratively defining milestones aligned with our upcoming deliverables. These milestones can potentially align with sprints, which typically span 2-4 weeks. However, we have chosen not to strictly follow milestone-sprint convention. This decision is because our final delivery date is set for November 23rd, and we've found it more straightforward to align each milestone directly with a deliverable.

The milestons help us organize and manage `issues` and `merge requests`. Therefore, we make sure to always link an issue to a milestone.

## Issues

We make sure to create issues, whenever we intend to perform actions such as creating, fixing, implementing, or renaming code.

We follow the principle, that the title of the issues should be concise and easy to understand at first glance. We're also making sure to write the descriptions clear and straightforward.

Another principle we follow: you can assign the issue to a specific team member (which we have discussed beforehand), but the person assigned to the issue <u>can not</u> be the one reviewing and approving it.

### Labels

By labelling the different issues, it opens for categorizing issues and other elements in gitlab. Some of the issues we've created and used so far:

- improvement
- test
- docs

## Branches

When working with issues in `git`, we want to work in separate branches. Our team's workflow centers around that a merge request is created, as soon as an issue is assigned to a team member. Afterward, the assigned team member uses the command `git checkout <name of the branch>`, and starts working in that branch, corresponding to the issue (s)he has been assigned to.

This allows each team member to work on their specific part of the project without conflicting with other work.

For instance, Katrine can comfortably operate in her designated branch, directly linked to issue #6(which is related to `docs`), while Elias is working on `#9`: _implementing JaCoCo_.

Once you think you are done with the issue, be sure to notify the designated reviewer, allowing them to review and approve the merge request before merging it into the `dev`-branch.

For now, we've designated the `dev`-branch as our 'main' branch, since the project remains a work in progress.

## Commit messages

We are following a certain git-commit-convention, which helps us understand the purpose and context of the code changed/implemented, at a quick glance. \

(_Notice_: The headings are always written in present tense!) \

We follow this convention:

```
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```

Here's an example:

```
test: create ui test for CustomerController

Used javafx and made sure the file extends ApplicationTest.


Co-Authored-By: Ben Adams <benadams@stud.ntnu.no>
```

### Co-authored-by

Over the past few weeks, as part of _Group deliverable 2_, we have been practicing pair programming. This involves teaming up in pairs and taking turns with the coding tasks. Additionally, we make an effort to review each other's code as we go along. We also make sure to include "Co-authored-by" in the commit footer, which tells who's been working together.

# Work flow

In accordance with certain [SCRUM](https://www.scrum.org/resources/what-scrum-module) principles, our workflow incorporates physical meetings, notably the "daily stand-up." During these meetings, we provide concise briefings on our upcoming tasks and develop a plan of action for the work session, as a kind of a "slagplan".

We also usually try to end our work session with a debrief, and reflect on our work so far.

### Open communication

If we have any uncertainties regarding code or design choices, we ask each other. We want an environment where questions and clarifications are encouraged, aiming for a collaborative and accessible workspace.

# Code quality

To ensure good code quality, we as team members make sure to communicate with each other, and look over each other's code - as mentioned previously.

## Testing

Testing is an important part of software development; you want to make sure that the code is working - not only during the "general" cases, but also the edge cases. As we have learned in the ITP lectures:

> […] _Automated testing allows to develop new features with a minimal effort to check if the software still works as expected_ \
> -- <cite>"Testing and code quality", Forelesning Uke 37</cite>

### Testing the logic

Hence, we've created tests for our core-manager classes and their methods, to guarantee that our logic functionality operates as expected.

This is because the `Manager`-classes serve as the bridge between the core logic and the UI, encapsulating our business logic. By concentrating our testing efforts on the `Manager`-files, we ensure that this critical logic - the "brain" of our project - is working as we want to.

This approach optimizes our testing efforts, as it allows us to comprehensively validate the core functionality of our application while avoiding the redundancy of testing lower-level components such as the model or fileutil classes, which are linked with the `Manager`-files.

We are testing with JUnit.

### Testing the UI

We are also making sure to test the controllers:
`CustomerController` and `RoomController`. We are extending the test-files with the `ApplicationTest`, and using a `FxRobot` to perform the actions we want, as if an user had clicked on the different buttons and views.

## Tools used to ensure code quality

### Jacoco

We are using _Java Code Coverage_ (JaCoCo) to measure the extent to which our code is being tested. It helps us identify code that has not been tested, and by increasing our code test coverage, it can indirectly assist us in ensuring better code quality.

### Spotbug

_Spotbug_ is being used to find bugs in our program. By looking at instances of "bug patterns", it will tell us that these are most likely to be errors. It also helps with styling in the project.

For example, when we initially integrated Spotbug and executed the command "`mvn verify`". This raised issues because of errors and code conventions violations, such as methods starting with lowercase letters.

(We located the errors, changed the name of some of the methods, and now it's working:))

### Checkstyle

_Checkstyle_ helps with maintaining consistent coding style and formatting, and finds errors like unused variables. We are using the default Checkstyle-xml.

In our project, we have had some issues with Checkstyle, mostly because of line indentation. This has now been fixed.
«