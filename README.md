# Length units converter


## USAGE
Converts length units to another length units. Input and output format - JSON.
Supported default units: m, cm, ft, in.

Input files:
1) Rules - specifies unit names and their convert value in meters (JSON array)
2) Input file - specifies, what units are converting (JSON object)

Output - JSON with unit name converted to and result value

Example rules.json:
```JSON
[
  {
    "unit_name": "km",
    "value": 1000.0
  },
  {
    "unit_name": "mm",
    "value": 0.001
  },
  {
    "unit_name": "yd",
    "value": 0.9144
  }
]
```

Example input.json:
```JSON
{
  "distance":
  {
    "unit": "yd",
    "value": 20.0
  },
  "convert_to": "cm"
}

```

## Build
```BASH
mvn clean package
```

to see usage specify
```
java -jar <converter-jar-file> -h
```
## Example
You can run run.sh script to build project and run program with example files

input.json:
```JSON
{
    "distance" :
    {
	"unit" : "cm",
	"value" : 32.0
    },
    "convert_to" : "m"
}
```


default.json:

```JSON
[
  {
    "unit_name": "in",
    "value": 0.0254
  },
  {
    "unit_name": "ft",
    "value": 0.3048
  },
  {
    "unit_name": "cm",
    "value": 0.01
  },
  {
    "unit_name": "m",
    "value": 1.0
  }
]
```


result:
```JSON
{
  "unit":"m",
  "value":0.32
}
```

## RELEASE
You can download latest jar [release 1.0-SNAPSHOT](https://github.com/kikuha21400/converter/releases/tag/java)



