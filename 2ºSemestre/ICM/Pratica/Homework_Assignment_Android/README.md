# ICM_108840
Aulas Práticas da Cadeira de Introdução à Computação Móvel 2023/24

## Homework Assignment 1

- study this example before (it is a hands-on with code-along video support) 
- create a simple application to manage your movies and series “watch list”
- you should be able to add entries to the Watch List (stuff you want to watch) and, later, you should be able to mark entries as already watched (e.g.: tick a box). For convenience, start the app with a few entries pre-filled.
- you should use Composables, stateless UI, a list and the ViewModel pattern.  Try to rotate the device while using the app  (the list  should not be lost with configuration changes…). 
- for now, it is not needed to save the information in a persistent way (i.e., save to a database). 


Notes and key ideias:

- Composition: a description of the UI built by Jetpack Compose when it executes composables. Initial composition: creation of a Composition by running composables the first time. Recomposition: re-running composables to update the Composition when data changes. To be able to do this, Compose needs to know what state to track.
- Use Compose's State and MutableState types to make state observable by Compose, i.e., wrap regular data into an observable/tracker object.
Pattern “state hoistitng”: Composables should be stateless, moving the state to “upper” scopes. The “upper” scope passes the required state (data) as parameter to the Composable as well as the callback functions to receive any events of interest unidirectional data flow.
- The ViewModel pattern allows to move the UI-state outside the UI definition. The ViewModel holds the data and the UI observes the  (state-wrapped) data, reacting to changes in state.
