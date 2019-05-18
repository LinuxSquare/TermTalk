# Gimmicks

## Banner / Alerts

Wenn an die Schlüsselwörter _Attention, Note, Hint_ direkt ein : angehängt wird, wird der folgende Satz farbig eingefärbt:

Attention: Das ist wichtig

Hint: Das könnte ein Tip sein

Note: Notiz...

## UML über yUML.me

Man kann UML direkt über den [yUML.me](yUML.me)-Service generieren...Beispiele:
Weitere Beispiele unter [https://yuml.me/diagram/scruffy/class/samples](https://yuml.me/diagram/scruffy/class/samples)

Aus
```
[gimmick:yuml]( [HttpContext]uses -.->[Response] )
```
wird:
[gimmick:yuml]( [HttpContext]uses -.->[Response] )

Aus
```
[gimmick:yuml (type: 'activity', style: 'plain') ]( `Make Coffee´->`want more coffee´ )
```
wird:
[gimmick:yuml (type: 'activity', style: 'plain') ]( `Make Coffee´->`want more coffee´ )

Aus
```
[gimmick:yuml (diag: 'usecase', scale: 150) ]( [Customer]-`Sign In´, [Customer]-`Buy Products´ )
```
wird:
[gimmick:yuml (diag: 'usecase', scale: 150) ]( [Customer]-`Sign In´, [Customer]-`Buy Products´ )


### Optionen

* direction
  * is one of [ 'TB', 'LR' ]
  * direction of the diagram: top-to-bottom or left-to-right
* scale
  * is an integer percentage value, i.e. 150 or 200
  * defines the scaling applied to the diagram in percent, 100% = no scaling
* type
  * is one of [ 'class', 'activity', 'usecase' ]
  * type of the UML diagram
* style
  * is one of [ 'plain', 'scruffy' ]
  * defines the applied theme, plain for clean and scruffy for comic-style look

## Mathe

Mathematische Formeln lassen sich schön darstellen...

Aus
```
[gimmick: math]()
$$ x = \frac{-b \pm \sqrt{b^2 - 4ac}}{2a} $$
```
wird:
[gimmick: math]()
$$ x = \frac{-b \pm \sqrt{b^2 - 4ac}}{2a} $$
