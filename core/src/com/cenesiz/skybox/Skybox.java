package com.cenesiz.skybox;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.IntAttribute;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class Skybox {

	private Color colorTop;
	private Color colorBottom;
	private Camera camera;
	private ModelInstance instance;
	private Vector3 scale;
	private Box box;

	public Skybox(Camera camera, Color colorTop, Color colorBottom, float scale) {
		this.camera = camera;
		this.colorTop = colorTop;
		this.colorBottom = colorBottom;
		box = new Box(colorTop, colorBottom);
		instance = new ModelInstance(box.model);
		this.setScale(scale);
	}

	public void update() {
		instance.transform.setToTranslationAndScaling(camera.position, scale);
	}

	public void render(ModelBatch batch) {
		batch.render(instance);
	}

	public void render(ModelBatch batch, Environment environment) {
		batch.render(instance, environment);
	}

	public void dispose() {
		box.dispose();
	}

	private void setColors(Color colorTop, Color colorBottom){
		this.colorTop = colorTop;
		this.colorBottom = colorBottom;
		box.setColors(colorTop, colorBottom);
	}

	private void lerpColors(Color colorTop, Color colorBottom, float alpha){
		this.colorTop.lerp(colorTop, alpha);
		this.colorBottom.lerp(colorBottom, alpha);
		box.setColors(this.colorTop, this.colorBottom);
	}

	private void setScale(float value) {
		if (scale == null)
			scale = new Vector3();
		scale.set(value, value, value);
	}

	public ModelInstance getInstance() {
		return instance;
	}

	public Vector3 getScale() {
		return scale;
	}

	static class Box{

		private final Model model;
		private Color colorTop;
		private Color colorBottom;

		private final Vector3 a = new Vector3(-1,1,1);
		private final Vector3 b = new Vector3(-1,1,-1);
		private final Vector3 c = new Vector3(1,1,-1);
		private final Vector3 d = new Vector3(1,1,1);
		private final Vector3 e = new Vector3(-1,-1,1);
		private final Vector3 f = new Vector3(-1,-1,-1);
		private final Vector3 g = new Vector3(1,-1,-1);
		private final Vector3 h = new Vector3(1,-1,1);

		private final float[] vertices = new float[252];

		public Box(Color colorTop, Color colorBottom) {
			this.colorTop = colorTop;
			this.colorBottom = colorBottom;


			ModelBuilder modelBuilder = new ModelBuilder();

			modelBuilder.begin();
			MeshPartBuilder meshPartBuilder = modelBuilder.part(
					"TRIANGLE",
					GL20.GL_TRIANGLES,
					3,
					new Material(
							new IntAttribute(IntAttribute.CullFace, 0),
							new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)
					)
			);

			createMeshParts(meshPartBuilder);

			model = modelBuilder.end();

			createBox();
			model.meshParts.get(0).mesh.setVertices(vertices);
		}



		private void createBox(){

			rectangle(
					0,
					a, colorTop,
					b, colorTop,
					c, colorTop,
					d, colorTop
			);

			rectangle(
					1,
					f, colorBottom,
					b, colorTop,
					a, colorTop,
					e, colorBottom
			);

			rectangle(
					2,
					e, colorBottom,
					a, colorTop,
					d, colorTop,
					h, colorBottom
			);

			rectangle(
					3,
					h, colorBottom,
					d, colorTop,
					c, colorTop,
					g, colorBottom
			);

			rectangle(
					4,
					f, colorBottom,
					e, colorBottom,
					h, colorBottom,
					g, colorBottom
			);

			rectangle(
					5,
					b, colorTop,
					f, colorBottom,
					g, colorBottom,
					c, colorTop
			);
		}

		private void setColors(Color colorTop, Color colorBottom){
			this.colorTop = colorTop;
			this.colorBottom = colorBottom;

			rectangle(
					0,
					a, colorTop,
					b, colorTop,
					c, colorTop,
					d, colorTop
			);

			rectangle(
					1,
					f, colorBottom,
					b, colorTop,
					a, colorTop,
					e, colorBottom
			);

			rectangle(
					2,
					e, colorBottom,
					a, colorTop,
					d, colorTop,
					h, colorBottom
			);

			rectangle(
					3,
					h, colorBottom,
					d, colorTop,
					c, colorTop,
					g, colorBottom
			);

			rectangle(
					4,
					f, colorBottom,
					e, colorBottom,
					h, colorBottom,
					g, colorBottom
			);

			rectangle(
					5,
					b, colorTop,
					f, colorBottom,
					g, colorBottom,
					c, colorTop
			);
			model.meshParts.get(0).mesh.setVertices(vertices);
		}

		private void rectangle(int index,
							   Vector3 p1, Color c1,
							   Vector3 p2, Color c2,
							   Vector3 p3, Color c3,
							   Vector3 p4, Color c4){

			int offset = index * 42;

			// Triangle 1

			vertices[offset] = p1.x;
			vertices[offset + 1] = p1.y;
			vertices[offset + 2] = p1.z;

			vertices[offset + 3] = c1.r;
			vertices[offset + 4] = c1.g;
			vertices[offset + 5] = c1.b;
			vertices[offset + 6] = c1.a;

			vertices[offset + 7] = p2.x;
			vertices[offset + 8] = p2.y;
			vertices[offset + 9] = p2.z;

			vertices[offset + 10] = c2.r;
			vertices[offset + 11] = c2.g;
			vertices[offset + 12] = c2.b;
			vertices[offset + 13] = c2.a;

			vertices[offset + 14] = p3.x;
			vertices[offset + 15] = p3.y;
			vertices[offset + 16] = p3.z;

			vertices[offset + 17] = c3.r;
			vertices[offset + 18] = c3.g;
			vertices[offset + 19] = c3.b;
			vertices[offset + 20] = c3.a;

			// Triangle 2

			vertices[offset + 21] = p1.x;
			vertices[offset + 22] = p1.y;
			vertices[offset + 23] = p1.z;

			vertices[offset + 24] = c1.r;
			vertices[offset + 25] = c1.g;
			vertices[offset + 26] = c1.b;
			vertices[offset + 27] = c1.a;

			vertices[offset + 28] = p3.x;
			vertices[offset + 29] = p3.y;
			vertices[offset + 30] = p3.z;

			vertices[offset + 31] = c3.r;
			vertices[offset + 32] = c3.g;
			vertices[offset + 33] = c3.b;
			vertices[offset + 34] = c3.a;

			vertices[offset + 35] = p4.x;
			vertices[offset + 36] = p4.y;
			vertices[offset + 37] = p4.z;

			vertices[offset + 38] = c4.r;
			vertices[offset + 39] = c4.g;
			vertices[offset + 40] = c4.b;
			vertices[offset + 41] = c4.a;


		}

		private void createMeshParts(MeshPartBuilder meshPartBuilder){
			for (int i = 0; i < 6; i++) {
				meshPartBuilder.triangle(
						a, Color.BLACK,
						b, Color.BLACK,
						c, Color.BLACK
				);
				meshPartBuilder.triangle(
						a, Color.BLACK,
						b, Color.BLACK,
						c, Color.BLACK
				);
			}
		}

		private void dispose(){
			model.dispose();
		}

	}

}

