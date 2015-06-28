CREATE TABLE IF NOT EXISTS `statistics` (
    `id` int(3) NOT NULL AUTOINCREMENT,
    `metro` varchar(100) not null,
    `price`` int not null,
    `site_inner_id` varchar(100) not null
    PRIMARY KEY (`id`)
);