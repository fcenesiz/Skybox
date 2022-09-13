### Gradient Color Skybox, easy to add and apply!

![](skybox.gif )

## Usage

### create

```Java
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

```Java
public void update(float deltaTime){
    ...
    skybox.update();
    ...
}
```

### render

```Java
public void render(ModelBatch batch){
    ...
    skybox.render(batch); // skybox.render(batch, envirounment);
    ...
}

```

#### other
they can be used at runtime.
```Java
setColors(
    Color.BLACK, // top color
    Color.BROWN // bottom color
);

lerpColors(
        Color.FOREST, // top color
        Color.GOLD // bottom color
);
```

