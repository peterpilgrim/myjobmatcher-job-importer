# Issues 

The original branch code was written as a prototype in 2-3 hours. 
It showed how the functionality, however it was not written for elegance and re-usability. 
The architect agreed with the client that the code was not fit for purpose, 
such that it could handed over to new graduate developer. 
 

##S - Single responsiblity

Required: a class should have only a single responsibility 
(i.e. only one potential change in the software's specification should be able to affect the specification of the class)

Prototype Code Code: the whole functionality is being handles by one class, e.g. the JobBundleReader class is performing mapping, reading and normalisation


 

## O - Open/Closed principle

Required: The software entities should be open for extension, but closed for modification

Prototype Code Code: 

    if (insideSentinel) {

                            final String localName = streamReader.getLocalName();

                            if (configuration.getTitleElementName().equalsIgnoreCase(localName)) {

                                jobRecord.setTitle(notNullAndNormalise(streamReader.getElementText()));

                            }

                            if (configuration.getDescriptionElementName().equalsIgnoreCase(localName)) {

                                jobRecord.setDescription(notNullAndNormalise(streamReader.getElementText()));

                            }

                            if (configuration.getLocationElementName().equalsIgnoreCase(localName)) {

                                jobRecord.setLocation(notNullAndNormalise(streamReader.getElementText()));

                            }

                            if (configuration.getReferenceElementName().equalsIgnoreCase(localName)) {

                                jobRecord.setReference(notNullAndNormalise(streamReader.getElementText()));

                            }

                            if (configuration.getUrlElementName().equalsIgnoreCase(localName)) {

                                jobRecord.setUrl(notNullAndNormalise(streamReader.getElementText()));

                            }

                        }

 

Failed on this principle as this class would have to be modified if there was a need to extend the mapping or change the mapping

 

## L - Liskov substitution principle

Required: objects in a program should be replaceable with instances of their subtypes without altering the correctness of that program

Prototype Code Code: the whole functionality is in a single class, including the 3rd party library for reading XML

 

## I -  Interface segregation principle

Required: many client-specific interfaces are better than one general-purpose interface

Prototype Code Code: there are no interfaces present at all

 

D - Dependency inversion principle

Required: dependency should be on abstractions, not concretions

## Prototype Code Code: the functionality is in one single class
