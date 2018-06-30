# micro_pong
Microservice pong

# API

```
GET
/game/ball_pos
output:"
{"x": int, "y": int}

POST
/game/player_pos
input:
{"id": int, "dir": "string"}

id should be 0 or 1
dir should be up or down

GET
/game/data
output:
{
	"ball_pos:"
	{
		"x": int,
		"y": int
	},
	"player_pos":
	[
		{"y": int}
		...
	]
}
```
