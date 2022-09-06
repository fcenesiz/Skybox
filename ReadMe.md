### Gradient Color Skybox, easy to add and apply!

![](skybox.gif )

## Usage

### create

```
private Skybox skybox;

public void create(){
    ...
    skybox = new Skybox(
        camera, // game camera
        Color.CYAN, // top color
        Color.YELLOW, // bottom color
        500 // scale
    );
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
public void render(ModelBatch batch){
    ...
    skybox.render(batch); // skybox.render(batch, envirounment);
    ...
}

```


