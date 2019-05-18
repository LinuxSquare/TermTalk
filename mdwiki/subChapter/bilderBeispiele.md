# Bilder // Beispiele

## Bilder aus dem Internet

Images are like hyperlinks, but with an exclamation mark in front of them:
![](http://placekitten.com/g/250/250)

## Interne Bilder

Interne Bilder werden über den lokalen Pfad eingebunden:

Beispiel:
```
![](subChapter/images/300.jpg "A kitten")
```

generiert:
![](subChapter/images/300.jpg "A kitten")

Wenn Sie Bilder direkt untereinander verlinken, werden sie nebeneinander dargestellt:
![](subChapter/images/450.jpg "First of two kittens")
![](subChapter/images/450.jpg "Second of two kittens")

Oder dreifach:
![](subChapter/images/350.jpg)
![](subChapter/images/350.jpg)
![](subChapter/images/350.jpg)

## Bilder als links

Sieht komisch aus:
```
[![ImageCaption](path/to/image.png)](http://www.linktarget.com)
```
Beispiel:
```
[![A kitten](http://placekitten.com/g/400/400)](http://www.placekitten.com)
```
generiert:

[![A kitten](http://placekitten.com/g/400/400)](http://www.placekitten.com)
