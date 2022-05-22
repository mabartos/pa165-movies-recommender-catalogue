package cz.fi.muni.pa165.movierecommender.core;

import cz.fi.muni.pa165.movierecommender.persistence.dao.MovieDao;
import cz.fi.muni.pa165.movierecommender.persistence.dao.PersonDao;
import cz.fi.muni.pa165.movierecommender.persistence.dao.UserDao;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Movie;
import cz.fi.muni.pa165.movierecommender.persistence.entity.Person;
import cz.fi.muni.pa165.movierecommender.persistence.entity.User;
import cz.fi.muni.pa165.movierecommender.persistence.enums.Genre;
import cz.fi.muni.pa165.movierecommender.persistence.enums.UserType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * @author Petr Šlézar
 */
@Slf4j
@Component
@Transactional
public class DataInitializer {

    private final UserDao systemUserDao;
    private final MovieDao movieDao;
    private final PersonDao personDao;
    private final PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();

    private Map<String, Long> movieIds = new HashMap<>();
    private Map<String, Long> personIds = new HashMap<>();
    private Map<String, Long> userIds = new HashMap<>();

    @Autowired
    public DataInitializer(UserDao systemUserDao, MovieDao movieDao, PersonDao personDao) {
        this.movieDao = movieDao;
        this.systemUserDao = systemUserDao;
        this.personDao = personDao;

    }

    @Transactional
    public void createUsers() {

        User admin = new User("admin", passwordEncoder.encode("admin"), "admin@gmail.com", UserType.ADMIN);
        User adminCreated = systemUserDao.create(admin);
        userIds.put("admin", adminCreated.getId());

        User pepa = new User("pepa", passwordEncoder.encode("pepa"), "pepa@gmail.com", UserType.BASIC_USER);
        User pepaCreated = systemUserDao.create(pepa);
        userIds.put("pepa", pepaCreated.getId());

        User karel = new User("karel", passwordEncoder.encode("karel"), "karel@gmail.com", UserType.BASIC_USER);
        User karelCreated = systemUserDao.create(karel);
        userIds.put("karel", karelCreated.getId());
    }

    @Transactional
    public void createMovies() {

        Movie shawshankRedemption = new Movie(null,"The Shawshank Redemption", 142,
                "https://play-lh.googleusercontent.com/hqVkofgQryL02Z2g_rsKpu923e2IMr_bXH7mCG0D0QOTLWT_a5qpv4zq3XxJ3vpcFm8hNnVkeLR6KYyi4ss",
                new HashSet<Genre>(List.of(Genre.CRIME, Genre.DRAMA)),
                "Mladý bankovní manažer Andy Dufresne (Tim Robbins) je v roce 1947 odsouzen na doživotí za vraždu své " +
                        "ženy a jejího milence, kterou nespáchal. Čeká ho trest v obávané věznici Shawshank. Andy se " +
                        "zde sblíží s černochem Redem (Morgan Freeman), jenž je tu už dvacet let, a během dlouhé doby se" +
                        " jim společně podaří dosáhnout zlepšení zdejších poměrů. Andy se dokonce stane strážcům i " +
                        "nenáviděnému řediteli věznice nepostradatelný jako daňový a finanční poradce.",
                1994, Collections.emptySet(), Collections.emptySet());
        movieIds.put("shawsankRedemption", movieDao.create(shawshankRedemption).getId());


        Movie greenMile = new Movie(null,"The Green Mile", 188,
                "https://img.csfd.cz/files/images/user/profile/159/077/159077077_bf2f37.png",
                new HashSet<Genre>(List.of(Genre.CRIME, Genre.DRAMA, Genre.MYSTERY)),
                "Paul Edgecomb se vrací ve vzpomínkách do roku 1935, kdy byl zaměstnán v louisianské věznici " +
                        "jako hlavní dozorce. Tenkrát se tam setkal s výjimečným, byť duchem prostým mužem, který byl " +
                        "obdařen nejen velkým srdcem, ale také nadpozemskými schopnostmi. Byl to John Coffey, neprávem " +
                        "odsouzený na smrt za vraždu dvou malých holčiček. V té době trpěl Paul těžkým zánětem močového " +
                        "měchýře a také neměl šanci zbavit se sadistického, všemi nenáviděného dozorce Percyho. Jednoho " +
                        "dne chce s Paulem mluvit Coffey. Když se k němu přiblíží, chytí ho rukou v rozkroku, pak " +
                        "šokovaného Paula pustí a on uvidí, jak černoch vypustil z úst černý oblak, a současně si " +
                        "uvědomí, že jeho bolestivý zánět zmizel. Coffey má zvláštní schopnost, díky které vyléčí ženu" +
                        "správce věznice, ke které ho tajně v noci převezou, dokonce oživí cvičenou myš jednoho vězně, " +
                        "kterou zabil zlomyslný Percy. John Coffey, kterého čeká smrt, přijímá svůj úděl odevzdaně a " +
                        "bez hořkosti.",
                1998, Collections.emptySet(), Collections.emptySet());
        movieIds.put("greenMile", movieDao.create(greenMile).getId());

        Movie magnificentSeven = new Movie(null,"The Magnificent Seven", 123,
                "https://i.ytimg.com/vi/8XDB7GMnbUQ/sddefault.jpg",
                new HashSet<Genre>(List.of(Genre.ADVENTURE, Genre.WESTERN)),
                "Nezapomenutelní pistolníci vyrážejí do boje proti banditům... Obyvatelé malé mexické vesnice" +
                        " umějí zacházet s motykami, ale ne s kolty a puškami. Skoro všechno, co se jim podaří lopotně " +
                        "vypěstovat, musejí proto odevzdávat zločinnému banditovi Calverovi a jeho mužům. Jednoho dne " +
                        "však dojde trpělivost i jim. Tři z nich se proto vydají k americké hranici. Tam by totiž mohli" +
                        " najít někoho, kdo je naučí bojovat a pomůže jim postavit se Calverovi na odpor. Jako první " +
                        "přijme jejich skromnou nabídku sympatický holohlavý pistolník Chris. ",
                1960, Collections.emptySet(),  Collections.emptySet());
        movieIds.put("magnificentSeven", movieDao.create(magnificentSeven).getId());

        Movie cuckoosNest = new Movie(null,"One Flew over the Cuckoo's Nest", 133,
                "https://m.media-amazon.com/images/I/61ah-D5Hc9L._AC_SY679_.jpg",
                new HashSet<Genre>(List.of(Genre.DRAMA)),
                "Předlohou k filmu Přelet nad kukaččím hnízdem byl režiséru Miloši Formanovi román Kena " +
                        "Keseyho, který ho psal při působení v „nemocnici pro veterány“. Snímek se stal největším " +
                        "mezinárodním úspěchem ve Formanově kariéře. V roce 1976 získal Oscara ve všech hlavních " +
                        "kategoriích – za nejlepší film, režii, mužský herecký výkon (Jack Nicholson), ženský herecký " +
                        "výkon (Louise Fletcherová) a scénář. Majitelem filmových a divadelních práv byl 13 let Kirk " +
                        "Douglas, který je postoupil svému synu Michaelovi a tím z něho udělal úspěšného producenta..." +
                        "Zrzavý irský rváč a profesionální karbaník Randle Patrick McMurphy si odpykává trest za " +
                        "opakované násilné napadení a sexuální zneužití. Místo aby se držel v pracovním táboře, hraje " +
                        "pomateného a nechá se poslat do zdánlivého klidu psychiatrické léčebny.",
                1975, Collections.emptySet(),  Collections.emptySet());
        movieIds.put("cuckoosNest", movieDao.create(cuckoosNest).getId());

        Movie marecek = new Movie(null,"Marecku, podejte mi pero!", 91,
                "https://m.media-amazon.com/images/I/61ah-D5Hc9L._AC_SY679_.jpg",
                new HashSet<Genre>(List.of(Genre.COMEDY)),
                "Jiří Kroupa, mistr v továrně na zemědělské stroje, je závodní radou donucen zasednout do " +
                        "lavice večerní průmyslovky. Potřebuje si totiž zvýšit kvalifikaci, aby mohl zůstat ve funkci " +
                        "i po chystané modernizaci podniku. S ním do lavic usedá pestrá směsice studentů - " +
                        "šplhoun Hujer, koketní paní Týfová, třídní rošťáci Tuček a Šlajs, zmatkář Plha nebo věčný " +
                        "ospalec Dudek. Zkrátka chybí jen Hliník, který se odstěhoval do Humpolce. Přestože jsou to " +
                        "vesměs lidé středního věku, znovu se mění ve školáky. ",
                1976, Collections.emptySet(),  Collections.emptySet());
        movieIds.put("marecek", movieDao.create(marecek).getId());

        Movie dogs = new Movie(null,"Reservoir dogs", 120,
                "https://m.media-amazon.com/images/M/MV5BZmExNmEwYWItYmQzOS00YjA5LTk2MjktZjEyZDE1Y2QxNjA1XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_FMjpg_UX1000_.jpg",
                new HashSet<Genre>(List.of(new Genre[]{Genre.ACTION, Genre.CRIME, Genre.THRILLER})),
                "About Reservoir Dogs", 1992, Collections.emptySet(),  Collections.emptySet());
        movieIds.put("dogs", movieDao.create(dogs).getId());

        Movie monty = new Movie(null,"Monty Python and Holy Grail", 120,
                "https://m.media-amazon.com/images/I/510HSMCnKsL._AC_.jpg",
                new HashSet<Genre>(List.of(new Genre[]{Genre.COMEDY, Genre.ADVENTURE, Genre.FANTASY, Genre.ACTION})),
                "About Monty Python and Holy Grail", 1982, Collections.emptySet(),  Collections.emptySet());
        movieIds.put("monty", movieDao.create(monty).getId());

        Movie pulpFiction = new Movie(null,"Pulp fiction", 120, "https://static.posters.cz/image/750/plakaty/pulp-fiction-cover-i1288.jpg",
                new HashSet<Genre>(List.of(new Genre[]{Genre.ACTION, Genre.CRIME, Genre.SLICE_OF_LIFE})),
                "About Pulp Fiction", 1994, Collections.emptySet(),  Collections.emptySet());
        movieIds.put("pulpFiction", movieDao.create(pulpFiction).getId());

    }

    @Transactional
    public void createPersons() {

        Person frankDarabont = new Person("Frank Darabont", LocalDate.of(1959, 01, 28),
                "About Frank",
                "https://image.pmgstatic.com/cache/resized/w100h132crop/files/images/creator/photos/162/616/162616697_7a42e4.png"
                , Collections.emptySet(), Collections.emptySet());
        personIds.put("frankDarabont", personDao.create(frankDarabont).getId());

        Person timRobbins = new Person("Tim Robbins", LocalDate.of(1958, 10, 16),
                "About Timothy Robbins",
                "https://image.pmgstatic.com/cache/resized/w100h132crop/files/images/creator/photos/163/921/163921450_a7778f.jpg"
                , Collections.emptySet(), Collections.emptySet());
        personIds.put("timRobbins", personDao.create(timRobbins).getId());

        Person morganFreeman = new Person("Morgan Freeman", LocalDate.of(1939, 6, 1),
                "About Morgan Freeman",
                "https://image.pmgstatic.com/cache/resized/w100h132crop/files/images/creator/photos/164/428/164428581_c81e3f.png"
                , Collections.emptySet(), Collections.emptySet());
        personIds.put("morganFreeman", personDao.create(morganFreeman).getId());

        Person tomHanks = new Person("Tom Hanks", LocalDate.of(1956, 7, 9),
                "About Tom Hanks",
                "https://image.pmgstatic.com/cache/resized/w100h132crop/files/images/creator/photos/163/996/163996832_9f4783.jpg"
                , Collections.emptySet(), Collections.emptySet());
        personIds.put("tomHanks", personDao.create(tomHanks).getId());

        Person michaelDuncan = new Person("Michael Duncan", LocalDate.of(1957, 12, 10),
                "About Clarke Duncan",
                "https://image.pmgstatic.com/cache/resized/w100h132crop/files/images/creator/photos/162/677/162677644_fc444a.jpg"
                , Collections.emptySet(), Collections.emptySet());
        personIds.put("michaelDuncan", personDao.create(michaelDuncan).getId());

        Person johnSturges = new Person("John Sturges", LocalDate.of(1910, 1, 3),
                "About John Sturges",
                "https://image.pmgstatic.com/files/images/creator/photos/000/269/269933_3a0f0a.jpg"
                , Collections.emptySet(), Collections.emptySet());
        personIds.put("johnSturges", personDao.create(johnSturges).getId());

        Person yulBrynner = new Person("Yul Brynner", LocalDate.of(1920, 7, 11),
                "About yulBrynner",
                "https://image.pmgstatic.com/cache/resized/w100h132crop/files/images/creator/photos/164/368/164368516_44aeda.png"
                , Collections.emptySet(), Collections.emptySet());
        personIds.put("yulBrynner", personDao.create(yulBrynner).getId());

        Person milosForman = new Person("Milos Forman", LocalDate.of(1932, 2, 10),
                "About Milos Forman",
                "https://image.pmgstatic.com/cache/resized/w100h132crop/files/images/creator/photos/165/528/165528862_917733.jpg"
                , Collections.emptySet(), Collections.emptySet());
        personIds.put("milosForman", personDao.create(milosForman).getId());

        Person jackNickolson = new Person("Jack Nicholson", LocalDate.of(1937, 4, 22),
                "About Jack Nicholson",
                "https://image.pmgstatic.com/cache/resized/w100h132crop/files/images/creator/photos/164/611/164611889_ce96ff.jpg"
                , Collections.emptySet(), Collections.emptySet());
        personIds.put("jackNickolson", personDao.create(jackNickolson).getId());

        Person oldrichLipsky = new Person("Oldrich Lipsky", LocalDate.of(1986, 7, 4),
                "About Oldrich Lipsky",
                "https://image.pmgstatic.com/files/images/creator/photos/000/269/269819_ce82e6.jpg"
                , Collections.emptySet(), Collections.emptySet());
        personIds.put("oldrichLipsky", personDao.create(oldrichLipsky).getId());

        Person jiriSovak = new Person("Jiri Sovak", LocalDate.of(1920, 12, 27),
                "About Oldrich Lipsky",
                "https://image.pmgstatic.com/files/images/creator/photos/000/268/268142_d299ab.jpg"
                , Collections.emptySet(), Collections.emptySet());
        personIds.put("jiriSovak", personDao.create(jiriSovak).getId());

        Person quentin =
                new Person("Quentin Tarantino", LocalDate.of(1963, Month.MARCH, 27),
                        "About Tarantino", "https://image.pmgstatic.com/cache/resized/w100h132crop/files/images/creator/photos/164/515/164515525_b98f8a.jpg",
                        Collections.emptySet(),
                        Collections.emptySet());
        personIds.put("quentin", personDao.create(quentin).getId());

        Person tim =
                new Person("Tim Roth", LocalDate.of(1961, Month.MAY, 14),
                        "About Roth", "https://image.pmgstatic.com/cache/resized/w100h132crop/files/images/creator/photos/162/387/162387459_f9d1c0.jpg",
                        Collections.emptySet(),
                        Collections.emptySet());
        personIds.put("tim", personDao.create(tim).getId());

        Person terry =
                new Person("Terry Jones", LocalDate.of(1942, Month.FEBRUARY, 1),
                        "About Terry", "https://image.pmgstatic.com/files/images/creator/photos/000/269/269441_e9126b.jpg"
                        , Collections.emptySet()
                        , Collections.emptySet());
        personIds.put("terry", personDao.create(terry).getId());
    }

    @Transactional
    public void connectFilmsAndPersons() {

        Movie shawsankRedemption = movieDao.findById(movieIds.get("shawsankRedemption"));
        Movie greenMile = movieDao.findById(movieIds.get("greenMile"));
        Movie magnificentSeven = movieDao.findById(movieIds.get("magnificentSeven"));
        Movie cuckoosNest = movieDao.findById(movieIds.get("cuckoosNest"));
        Movie marecek = movieDao.findById(movieIds.get("marecek"));
        Movie dogs = movieDao.findById(movieIds.get("dogs"));
        Movie monty = movieDao.findById(movieIds.get("monty"));
        Movie pulpFiction = movieDao.findById(movieIds.get("pulpFiction"));

        Person frankDarabont = personDao.findById(personIds.get("frankDarabont"));
        Person timRobbins = personDao.findById(personIds.get("timRobbins"));
        Person morganFreeman = personDao.findById(personIds.get("morganFreeman"));
        Person tomHanks = personDao.findById(personIds.get("tomHanks"));
        Person michaelDuncan = personDao.findById(personIds.get("michaelDuncan"));
        Person johnSturges = personDao.findById(personIds.get("johnSturges"));
        Person yulBrynner = personDao.findById(personIds.get("yulBrynner"));
        Person milosForman = personDao.findById(personIds.get("milosForman"));
        Person jackNickolson = personDao.findById(personIds.get("jackNickolson"));
        Person oldrichLipsky = personDao.findById(personIds.get("oldrichLipsky"));
        Person jiriSovak = personDao.findById(personIds.get("jiriSovak"));
        Person quentin = personDao.findById(personIds.get("quentin"));
        Person tim = personDao.findById(personIds.get("tim"));
        Person terry = personDao.findById(personIds.get("terry"));

        shawsankRedemption.setDirector(frankDarabont);
        shawsankRedemption.setActors(new HashSet<>(List.of(timRobbins, morganFreeman)));
        movieDao.update(shawsankRedemption);

        greenMile.setDirector(frankDarabont);
        greenMile.setActors(new HashSet<>(List.of(tomHanks, michaelDuncan)));
        movieDao.update(greenMile);

        magnificentSeven.setDirector(johnSturges);
        magnificentSeven.setActors(new HashSet<>(List.of(yulBrynner)));
        movieDao.update(magnificentSeven);

        cuckoosNest.setDirector(milosForman);
        cuckoosNest.setActors(new HashSet<>(List.of(jackNickolson)));
        movieDao.update(cuckoosNest);

        marecek.setDirector(oldrichLipsky);
        marecek.setActors(new HashSet<>(List.of(jiriSovak)));
        movieDao.update(marecek);

        dogs.setDirector(quentin);
        marecek.setActors(new HashSet<>(List.of(quentin, tim)));
        movieDao.update(dogs);

        monty.setDirector(terry);
        marecek.setActors(new HashSet<>(List.of(terry)));
        movieDao.update(monty);

        pulpFiction.setDirector(quentin);
        pulpFiction.setActors(new HashSet<>(List.of(quentin, tim)));
        movieDao.update(pulpFiction);

        frankDarabont.setDirectedMovies(new HashSet<>(List.of(shawsankRedemption, greenMile)));
        timRobbins.setActedInMovies(new HashSet<>(List.of(shawsankRedemption)));
        morganFreeman.setActedInMovies(new HashSet<>(List.of(shawsankRedemption)));
        tomHanks.setActedInMovies(new HashSet<>(List.of(greenMile)));
        michaelDuncan.setActedInMovies(new HashSet<>(List.of(greenMile)));
        johnSturges.setDirectedMovies(new HashSet<>(List.of(magnificentSeven)));
        yulBrynner.setActedInMovies(new HashSet<>(List.of(magnificentSeven)));
        milosForman.setDirectedMovies(new HashSet<>(List.of(cuckoosNest)));
        jackNickolson.setActedInMovies(new HashSet<>(List.of(cuckoosNest)));
        oldrichLipsky.setDirectedMovies(new HashSet<>(List.of(marecek)));
        jiriSovak.setActedInMovies(new HashSet<>(List.of(marecek)));
        quentin.setDirectedMovies(new HashSet<>(List.of(pulpFiction, dogs)));
        quentin.setActedInMovies(new HashSet<>(List.of(pulpFiction, dogs)));
        tim.setActedInMovies(new HashSet<>(List.of(pulpFiction, dogs)));
        terry.setDirectedMovies(new HashSet<>(List.of(monty)));
        terry.setActedInMovies(new HashSet<>(List.of(monty)));
        personDao.update(frankDarabont);
        personDao.update(timRobbins);
        personDao.update(morganFreeman);
        personDao.update(tomHanks);
        personDao.update(michaelDuncan);
        personDao.update(johnSturges);
        personDao.update(yulBrynner);
        personDao.update(milosForman);
        personDao.update(jackNickolson);
        personDao.update(oldrichLipsky);
        personDao.update(jiriSovak);
        personDao.update(quentin);
        personDao.update(tim);
        personDao.update(terry);
    }

}
