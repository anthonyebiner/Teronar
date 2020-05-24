package com.bears.teronar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class MainMenuScreen implements Screen {
    final Teronar game;
    OrthographicCamera camera;

    public MainMenuScreen(final Teronar game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.screenSizeX, game.screenSizeY);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.font.draw(game.batch, "Welcome to Teronar", game.screenSizeX/2-100, game.screenSizeY/2+200);
        game.font.draw(game.batch, "The Medieval Age is at its peak in the " +
                "magic-filled world of Elysia, where legions of steel knights" +
                " and arcane sorcerers \nare fighting side-by-side on the " +
                "battlefield as the rulers of the land wage wars of conquest " +
                "against each other to establish \ntheir dominance.\n" +
                "\n" +
                "Yet one nation has distanced itself from such volatile and " +
                "deadly conflicts, its monarchy believing these struggles to \n" +
                "be futile: the Kingdom of Teronar. As nationalism spreads " +
                "throughout the continent, tensions between the kingdom’s \n" +
                "people and those of neighboring lands continue to mount. " +
                "Finally, angered by the unwavering neutrality of the \n" +
                "government, a group of treacherous citizens revolt against " +
                "the monarchy.\n" +
                "\n" +
                "Armed with black market weapons and aided by sympathizers " +
                "from the kingdom’s army, the rebels storm the royal palace. \n" +
                "While the loyal members of the guard bravely rush to the " +
                "gates to fend off the attackers, defectors overpower the \n" +
                "royal protective escorts and take the monarch and their " +
                "family to be executed in the dungeons deep below the palace." +
                " \n" +
                "\n" +
                "Just when all hope seems lost, miraculously, the Grand " +
                "Sorcerer of Teronar teleports into the dungeons to rescue \n" +
                "the monarch, unshackling them and desperately casting a " +
                "powerful reality-bending spell just as the defectors rush to \n" +
                " eliminate the threat… ", game.screenSizeX/2-400, game.screenSizeY/2+130);
        game.font.draw(game.batch, "Press any key to begin", game.screenSizeX/2-110, game.screenSizeY/2-200);
        game.batch.end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
