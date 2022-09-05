### Gradient Color Skybox, easy to add and apply!

### Example
![](skybox.gif )

## Usage

### create

```
private Skybox skybox;

public void create(){
    ...
    skybox = new Skybox(camera, Color.CYAN, Color.YELLOW, 500);
    ...
}

```
### update

```
public void update(float deltaTime){
    ...
    skybox.update();
    ...
}
```

### render

```
private Skybox skybox;

public void create(){
    ...
    skybox.render(batch); // skybox.render(batch, envirounment);
    ...
}

```


