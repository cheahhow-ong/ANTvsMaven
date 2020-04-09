### **Side Quest Objective:**
- Write a simple command line program that accepts input as command line arguments and does some basic CRUD operations on a sqllite DB
- You cannot start off using Maven
- Dust off the grand daddy of java build tools..Ant...and use that to compile your application into a jar and run it.
- Move on from Ant to maven
- Come and explain to me differences you observed as you progressed

### **Differences between ANT & Maven:**
- Ant doesn't have formal conventions. You have to tell Ant exactly where to find the source, where to put the outputs, etc.
- Ant is procedural. You have to tell Ant exactly what to do; tell it to compile, copy, then compress, etc.
- Ant doesn't have a lifecycle.

- Maven uses conventions. It knows where your source code is automatically, as long as you follow these conventions.
- Maven is declarative; All you have to do is create a pom.xml file and put your source in the default directory. Maven will take care of the rest.
- Maven has a lifecycle. You simply call `mvn install` and a series of sequence steps are executed.

### **Benefit of using Maven:**
- Less configuration
- Dependency Management
- Consistent and common interface to build java projects
- Enforced standard naming convention, act as project management tool and generate reports etc