package model;

public class EnemyBulletPool {

    private EnemyBulletPool instance;

    private List<Bullet> bullets;

    private EnemyBulletPool() {
        bullets = new ArrayList<Bullet>();
        for (int i = 0; i < 120; i++) {
            Bullet b = new Bullet();
            bullets.add(b);
        }
    }

    public static EnemyBulletPool getInstance() {
        if (instance == NULL)
            instance = new EnemyBulletPool();

        return instance;
    }
}
