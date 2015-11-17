# TranslogToolset
A toolset for postprocessing Translog® files

## JAR Script

If you want a JAR script that converts Translog® files to product series files, specify in the manifest file that ConvertTranslogFilesToProductSeriesFiles is the main class and create a JAR file with that manifest. To run the JAR script, type in the following command in the command line:

```bash
java -jar <name-of-jar-file> <translog-file-to-convert>
```

## API

This toolset can also be used as an API. For that purpose, check how the ConvertTranslogFilesToProductSeriesFiles class for an example of how to use the toolset.
