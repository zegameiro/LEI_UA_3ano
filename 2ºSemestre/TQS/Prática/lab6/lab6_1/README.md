# Sonar Qube analysis from euromillions project

My project passed the defined quality gate in SonarQube.

| Issue | Problem Description | How to Solve |
| :---: | :---: | :---: |
| Vulnerability | The use of PseudoRandom number generator is a measure that can affect security | Use secureRandom instead of a normal random generator |
| Major Code Smell | The arguments in the assertThat function are in the following order: actual value and expected value | Swap the arguments so they are in the correct order expected value |
| Major Code Smell | Invok method(s) only conditionally | Use the method isDebugEnabled() to check if the debug level is enabled before evaluating the arguments. |
| Major Code Smell | Assign the loop increment within the loop body | Place the ```i++``` in the begining of loop definition like this ```for (int i = 0; i < STARS_REQUIRED; i++)``` instead of placing it at the end of the loop |
| Minor Code Smell | Lack of compliance with the Java Language Specification (JLS) | Reorder the modifiers ```static public``` to ```public static``` |
| Minor Code Smell | The return type should be an interface instead of a implementation | Change the return type ```ArrayList``` to ```List``` |
| Minor Code Smell | The existance of a specification in the constructor ```ArrayList<Dip>``` | Replace it with the diamond operator ```ArrayList<>``` |
