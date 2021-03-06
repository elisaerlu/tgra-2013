package is.ru.tgra;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


public class First3D_Core implements ApplicationListener {
	Camera cam;

	Landscape hmap;
	Landscape bpatch;

	
	@Override
	public void create() {

		Gdx.gl11.glEnable(GL11.GL_DEPTH_TEST);
		Gdx.gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		// Set up the projection matrix.
		Gdx.gl11.glMatrixMode(GL11.GL_PROJECTION);
		Gdx.gl11.glLoadIdentity();
		Gdx.glu.gluPerspective(Gdx.gl11, 90, 1.333333f, 0.02f, 30.0f);

		Gdx.gl11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		
		// Initialize the camera.
		cam = new Camera(new Point3D(1.0f, 2.5f, 4.0f), new Point3D(0.0f, 2.5f, 0.0f), new Vector3D(0.0f, 1.0f, 0.0f));
		
		// Create height map.
		hmap = Landscape.createRandomHeightMap(new Point3D(0.0f, 0.0f, 0.0f), 5.0f, 6.0f, 200, 200, 0.035f);
		
		// Create BezierPatch
		bpatch = Landscape.createBicubicBezierPatch(
				new Point3D[][] {
						{
						new Point3D(0.0f, 0.0f, 0.0f), new Point3D(0.0f, 2.0f, 1.0f), new Point3D(0.0f, 2.0f, 2.0f), new Point3D(0.0f, 0.0f, 3.0f)},
						{new Point3D(1.0f, 2.0f, 0.0f), new Point3D(1.0f, 0.0f, 1.0f), new Point3D(1.0f, 0.0f, 2.0f), new Point3D(1.0f, 2.0f, 3.0f)},
						{new Point3D(2.0f, 2.0f, 0.0f), new Point3D(2.0f, 0.0f, 1.0f), new Point3D(2.0f, 0.0f, 2.0f), new Point3D(2.0f, 2.0f, 3.0f)},
						{new Point3D(3.0f, 0.0f, 0.0f), new Point3D(3.0f, 2.0f, 1.0f), new Point3D(3.0f, 2.0f, 2.0f), new Point3D(3.0f, 0.0f, 3.0f)
						}
				},
				200, 200);
	}

	@Override
	public void dispose() {	}

	@Override
	public void pause() { }

	
	private void update() {
		float deltaTime = Gdx.graphics.getDeltaTime();

		if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
			cam.pitch(-90.0f * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			cam.pitch(90.0f * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			cam.yaw(-90.0f * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			cam.yaw(90.0f * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W)) {
			cam.slide(0.0f, 0.0f, -10.0f * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			cam.slide(0.0f, 0.0f, 10.0f * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A)) {
			cam.slide(-10.0f * deltaTime, 0.0f, 0.0f);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			cam.slide(10.0f * deltaTime, 0.0f, 0.0f);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.R)) {
			cam.slide(0.0f, 10.0f * deltaTime, 0.0f);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.F)) {
			cam.slide(0.0f, -10.0f * deltaTime, 0.0f);
		}		
	}


	
	private void display() {
		
		Gdx.gl11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);

		cam.setModelViewMatrix();
		
		Gdx.gl11.glEnable(GL11.GL_LIGHTING);
		Gdx.gl11.glEnable(GL11.GL_LIGHT0);
		Gdx.gl11.glEnable(GL11.GL_LIGHT1);
		//
		
		float[] lightDiffuse = {0.1f, 0.3f, 0.0f, 1.0f};
		Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, lightDiffuse, 0);

		float[] lightPosition = {0.0f, 3.0f, 0.0f, 1.0f};
		Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPosition, 0);

		float[] lightDiffuse1 = {0.3f, 0.5f, 0.5f, 1.0f};
		Gdx.gl11.glLightfv(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, lightDiffuse1, 0);

		float[] lightPosition1 = {0.0f, 4.0f, 0.0f, 1.0f};
		Gdx.gl11.glLightfv(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPosition1, 0);

		float[] materialDiffuse = {1.0f, 1.0f, 1.0f, 0.0f};
		Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_DIFFUSE, materialDiffuse, 0);

		Gdx.gl11.glPushMatrix();		
		hmap.draw();
		Gdx.gl11.glPopMatrix();
		bpatch.draw();
	}

	@Override
	public void render()
	{
		update();
		display();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}
}
