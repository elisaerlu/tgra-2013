package is.ru.tgra;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


public class BoxWorldListener implements ApplicationListener
{
	private Camera cam;
	private Cube cube;
	private float elapsedTime = 0;
	
	@Override
	public void create()
	{
		
		this.cube = new Cube("9452.jpg");
		
		Gdx.gl11.glEnable(GL11.GL_LIGHTING);
		Gdx.gl11.glEnable(GL11.GL_LIGHT0);
		Gdx.gl11.glEnable(GL11.GL_LIGHT1);
		Gdx.gl11.glEnable(GL11.GL_DEPTH_TEST);
		
		Gdx.gl11.glEnable(GL11.GL_NORMALIZE);
		
		Gdx.gl11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		Gdx.gl11.glMatrixMode(GL11.GL_PROJECTION);
		Gdx.gl11.glLoadIdentity();
		Gdx.glu.gluPerspective(Gdx.gl11, 90, 1.333333f, 0.02f, 30.0f);
		
		

		Gdx.gl11.glEnableClientState(GL11.GL_VERTEX_ARRAY);

		cam = new Camera(new Point3D(-2.5f, 0.2f, 1.5f), new Point3D(0.0f, 0.0f, 0.0f), new Vector3D(0.0f, 1.0f, 0.0f));
		
		elapsedTime = 0.0f;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	
	private void update()
	{
		float deltaTime = Gdx.graphics.getDeltaTime();
		this.elapsedTime += deltaTime;
		
		// Update the cube
		this.cube.update(this.elapsedTime);
		
		
		if(Gdx.input.isKeyPressed(Input.Keys.Q))
			Gdx.app.exit();

		if(Gdx.input.isKeyPressed(Input.Keys.UP))
		{
			cam.pitch(-90.0f * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
		{
			cam.pitch(90.0f * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			cam.yaw(-90.0f * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			cam.yaw(90.0f * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.W))
		{
			cam.slide(0.0f, 0.0f, -10.0f * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S))
		{
			cam.slide(0.0f, 0.0f, 10.0f * deltaTime);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A))
		{
			cam.slide(-10.0f * deltaTime, 0.0f, 0.0f);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D))
		{
			cam.slide(10.0f * deltaTime, 0.0f, 0.0f);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.R))
		{
			cam.slide(0.0f, 10.0f * deltaTime, 0.0f);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.F))
		{
			cam.slide(0.0f, -10.0f * deltaTime, 0.0f);
		}

	}

	private void display()
	{
		Gdx.gl11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);

		cam.setModelViewMatrix();
		

		float[] lightDiffuse = {1.0f, 1.0f, 1.0f, 1.0f};
		Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, lightDiffuse, 0);

		float[] lightPosition = {5.0f, 10.0f, 15.0f, 1.0f};
		Gdx.gl11.glLightfv(GL11.GL_LIGHT0, GL11.GL_POSITION, lightPosition, 0);

		float[] lightDiffuse1 = {0.5f, 0.5f, 0.5f, 1.0f};
		Gdx.gl11.glLightfv(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, lightDiffuse1, 0);

		float[] lightPosition1 = {-5.0f, -10.0f, -15.0f, 1.0f};
		Gdx.gl11.glLightfv(GL11.GL_LIGHT1, GL11.GL_POSITION, lightPosition1, 0);

		float[] materialDiffuse = {1.0f, 1.0f, 1.0f, 1.0f};
		Gdx.gl11.glMaterialfv(GL11.GL_FRONT, GL11.GL_DIFFUSE, materialDiffuse, 0);

		// draw the cube.
		this.cube.draw();
	
	}

	@Override
	public void render() {
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