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

And the class diagrams

![UML Package diagram](src/design/CD01%20Job%20Bundle%20Importer%20Classes.png)

and

![UML Package diagram](src/design/CD02%20Job%20Bundle%20Packages.png)





# Original Exercise

Here follows the original requirement. Notice there is mention of functionality, performance or reusability in this client's prospective design requirement.
The client did not furnish me with a sample XML file. So the one in this project is complete made up by me.
I applied TDD to this piece of speculative work and yet the feedback was a dressing down SOLID principles after I contributed 2-3 hours. 



## Overview

We are a job aggregator that receives XML job content from multiple job boards, 
all who may have their own XML file structure. We need to make sense of data from all 
these multiple sources.

## Challenge

To product code to import job data from any XML file that contains a large volume of 
job advert information. 
You must normalise the fields and save the structured results to ElasticSearch.

## Parameters
 
 1. The XML file may contain up to 2 million nodes with it.
 2. The XML file structure is not normalized
 3. The output structure to be saved to Elasticsearch should be as follows:
    * title: String
    * description: String
    * location: String
    * reference: String
    * url: String
    
 4. Description and tile normalised to the plain UTF-8 text (without html tags or non UTF characters)
 
## Tasks

 1. Produce a UML (Deployment diagram or sequence diagram)
 2. Implement the code
 
Thanks, My Job Matcher team





# TODO

Aggregate the test classes and production classes into sensible module. Sit with the business and decide if this is the correct strategy to solve data load issues.



# Software requirements

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
    
    
Run the main application 

    > gradle run
    

    
## Additional Notes

After the initial feedback, I was annoyed enought to I decided to show them SOLID, the full UML provide a class diagram
This piece of speculative test work was at least one day of work possibly one and half. That is a lot time for
a contractor let alone FTE to invest in an interview engagement.


Weld SE standalone dependencies for version 2.2.4.Final

http://stackoverflow.com/questions/25396614/does-cdi-work-for-regular-java-application
   


# Credits


Peter Pilgrim
Thursday 9th March 2017
Web: www.xenonique.co.uk
Twitter: peter_pilgrim (please follow me!)
