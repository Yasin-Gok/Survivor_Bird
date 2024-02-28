package com.yasingok.survivorbirdstarter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SurvivorBird extends ApplicationAdapter {

	SpriteBatch batch;
	Texture background, defaultPlayer;
	Bird sapkali, sari, yarasa, pembe, gri, siyahDusman, sariDusman, kirmiziDusman, player, enemy;
	Bird sapkaliBitis, sariBitis, yarasaBitis, pembeBitis, griBitis, bitis;
	float birdx, birdy;
	int gameState = 0;
	float speed = 0;
	float gravity = 1.5f;
	
	@Override
	public void create () {		// Normal onCreate kısmı bu oluyor
		batch = new SpriteBatch();
		background = new Texture("Full-Background.png");		// Tanımladık ve altta çizmek lazım
		defaultPlayer = new Texture("bird1_1.png");

		// 2 resimli olanlar
		sari = new Bird(0.25f,"bird2_1.png", "bird2_2.png");
		pembe = new Bird(0.25f,"bird4_1.png", "bird4_2.png");
		gri = new Bird(0.25f,"bird5_1.png", "bird5_2.png");

		// Düşman
		siyahDusman = new Bird(0.25f,"enemy1_1.png", "enemy1_2.png");
		sariDusman = new Bird(0.25f,"enemy2_1.png", "enemy2_2.png");
		kirmiziDusman = new Bird(0.25f,"enemy3_1.png", "enemy3_2.png");

		// 3 resimli
		sapkali = new Bird(0.20f,"bird1_1.png", "bird1_2.png", "bird1_3.png");

		// 4 resimli
		yarasa = new Bird(0.15f,"bird3_1.png", "bird3_2.png", "bird3_3.png", "bird3_4.png");

		sariBitis = new Bird(0.25f,"bird2_1.png");
		pembeBitis = new Bird(0.25f,"bird4_1.png");
		griBitis = new Bird(0.25f,"bird5_1.png");
		sapkaliBitis = new Bird(0.20f,"bird1_1.png");
		yarasaBitis = new Bird(0.15f,"bird3_1.png");

		player = yarasa;
		enemy = siyahDusman;
		bitis = yarasaBitis;

		birdx = (float) Gdx.graphics.getWidth() /2 - (float) player.getWidth();
		birdy = (float) Gdx.graphics.getHeight() /3;

		player.setPosition(birdx, birdy);
	}

	@Override
	public void render () {		// Sürekli olan şeyler burada geçmeli
		batch.begin();		// Başlangıç
		// Ekran boyutu kadar olmasını sağlıyoruz
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		if (gameState == 1){	// Oyun başladıysa
			if (Gdx.input.justTouched()){
				speed = -25;
			}

			speed += gravity; // Yerçekimini ekleyerek hızı artır
			birdy = birdy - speed;

			// Kuşun üst yüzeye çarpıp çarpmadığını kontrol et
			if (birdy + Gdx.graphics.getHeight() / 10>= Gdx.graphics.getHeight()) {
				birdy = Gdx.graphics.getHeight()- Gdx.graphics.getHeight() / 10; // Kuşun konumunu üst yüzeye set et
				System.out.println("UST");
				speed = 0;
			}

			// Kuşun alt kenara ulaştığını kontrol et
			if (birdy + Gdx.graphics.getHeight() / 10 <= 0) {
				gameState = 2; // Oyunu bitir
				birdy = 0; // Kuşun yüksekliğini sıfırla (isteğe bağlı olarak başlangıç yüksekliği)
				speed = 0; // Hızı sıfırla
				player = bitis;
			}
		}
		else {
			if(gameState != 2){
				if (Gdx.input.justTouched()){
					gameState = 1;
				}
			}
		}

		// Bird sınıfının draw metodunu kullanarak kuşları çiz
		player.draw(batch);
		player.setPosition(birdx, birdy);
		// Bird sınıfının update metodunu çağırarak animasyonu güncelle
		player.update(Gdx.graphics.getDeltaTime());
		batch.end();		// Bitiş
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		player.dispose();
		enemy.dispose();
		// Diğer kuş ve düşman nesnelerini de dispose et
		sari.dispose();
		pembe.dispose();
		gri.dispose();
		siyahDusman.dispose();
		sariDusman.dispose();
		kirmiziDusman.dispose();
		sapkali.dispose();
		yarasa.dispose();
	}
}