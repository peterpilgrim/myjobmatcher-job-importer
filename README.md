# My Job Matcher Java Candidate Exercise

This was my project to answer the [My Job Matcher](http://myjobmatcher.com] large volume data importer 
 

My solution uses the Streaming XML API to efficiently parse the XML data volume.

I made some assumptions. 

 * An importer must be flexible and therefore configurable. 
 * XML element names are identifiable and invariant inside the job board XML
 * There is usually a sentinel XML node that demarcate the start and the beginning of a job advert
 * The content is inside the element tag's body content text and not inside element's attribute
 
 
I used Java properties to configure the XML element tag names. 
I am unfamiliar with the Elastic Search API. 
  

I used an old [Astah UML Professional (version 6)](http://astah.net/editions/professional) to create the UML sequence diagram. You can find the UML diagram under the folder `src/design' 



![UML Sequence Diagram for the exercise](src/design/SD01%20Job%20Bundle%20Importer.png)




## TODO

Aggregate the test classes and production classes into sensible module. Sit with the business and decide if this is the correct strategy to solve data load issues.



## Software requirements

* Java 8 (JDK 1.8.0_60 or better) - I am using JDK 8 update 121
* Gradle 3.2 or better - I am using Gradle 3.4.1
* Access to the Internet!


## License
See `LICENSE.txt' for the license for this source code


## Building the software

Use Gradle from the command line or a suitable IDE like JetBrains IDEA, Eclipse or NetBeans

From the command line, clean the build beforehandL

    > gradle clean 
    
Build the software from scratch:
    
    > gradle build
    
Run the unit tests

    > gradle test --info
    
    
    
    


# Credits


Peter Pilgrim
Thursday 9th March 2017
Web: www.xenonique.co.uk
Twitter: peter_pilgrim (please follow me!)


    
    
    
