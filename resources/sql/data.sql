INSERT INTO `topics` (`title`, `description`)
VALUES
    ('Développement Web', 'Discussion sur les dernières tendances et technologies en développement web.'),
    ('Développement Informatique', 'Espace pour partager des connaissances et des expériences en développement informatique général.'),
    ('Langage Java', 'Forum pour discuter des meilleures pratiques, des astuces et des problèmes courants en Java.');

INSERT INTO `topics` (`title`, `description`)
VALUES
    ('Spring Boot', 'Discussion sur le développement d\'applications avec Spring Boot.'),
('JavaScript', 'Espace pour discuter des nouveautés et des problèmes courants en JavaScript.'),
('DevOps', 'Forum pour partager des expériences et des connaissances sur les pratiques DevOps.');


INSERT INTO `posts` (`author`, `topic_id`, `title`, `content`) VALUES
('Leo', 1, 'Article', 'This is an article about web development.'),
('Tim', 2, 'Web', 'Web development is a fascinating field.'),
('Justine', 3, 'Languages', 'There are many languages used in web development.'),
('Clara', 4, 'Essentials', 'HTML, CSS, and JavaScript are essential for web development.'),
('Emma', 1, ‘Angular Revolution’, 'Frameworks can simplify web development.'),
('Jules', 2, ‘IA is taking control’, 'Web development trends change rapidly.'),
('Liam', 3, ‘Accessibility’ , 'Responsive design is important in web development.'),
('Jonathan', 4, ‘Fullstack’, 'Web development includes both front-end and back-end development.'),
('Melvin', 1, 'Change', 'Web development requires continuous learning.'),
('Max', 2, 'Success', 'Web development can be a rewarding career.');
