import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Board {
	private int width;
	private int height;
	private int score;
	private Piece[][] arrayOfPieces;
	private Piece firstPressed;
	private Piece secondPressed;
	private Exception NullPointerException;
	private static boolean rightSequence = false;
	private static boolean leftSequence = false;
	private static boolean upSequence = false;
	private static boolean downSequence = false;
	private static boolean horizontalMiddleSequence = false;
	private static boolean verticalMiddleSequence = false;
	private static GameWindow window;
	private SpecialCrushes specialCrush;
	private Level gameLevel;
	private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);

	/**
	 * @requires parameter width != 0 , height != 0
	 * @param width
	 * @param height
	 * @modifies this width, this height, arrayOfPieces
	 * @ensures this width and this height must be initialized by width and
	 *          height, respectively. Also,arrayOfPieces is initialized.
	 */
	public Board(int width, int height, Level level) {
		this.width = width;
		this.height = height;
		score = 0;
		handleScoreModification();
		arrayOfPieces = new Piece[width][height];
		window = new GameWindow();
		specialCrush = new SpecialCrushes(this);
		gameLevel = level;
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		PCS.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		PCS.removePropertyChangeListener(l);
	}

	public void handleScoreModification() {
		PCS.firePropertyChange("score", -1, score);
	}

	/**
	 * @requires gameLevel != null
	 * @param gameLevel
	 * @modifies arrayOfPieces,scoreBox,starBox, movementBox,targetBox and
	 *           boardFrame.
	 * @ensures Lokums and obstacles are randomly created and they are put into
	 *          arrayOfPieces. According to the fields of game level, boxes are
	 *          initialized and board frame is displayed.
	 */

	public void initBoard() {
		if (gameLevel != null) {

			for (int i = 0; i < width; i++) {

				for (int j = 0; j < height; j++) {

					arrayOfPieces[j][i] = createRandomLokum();

					arrayOfPieces[j][i].setPosX(i);

					arrayOfPieces[j][i].setPosY(j);

					if (j > 1 && i > 1) {
						while (checkSequenceLeft(arrayOfPieces[j][i])
								|| checkSequenceUp(arrayOfPieces[j][i])) {

							arrayOfPieces[j][i] = null;

							arrayOfPieces[j][i] = createRandomLokum();

							arrayOfPieces[j][i].setPosX(i);

							arrayOfPieces[j][i].setPosY(j);
						}
					}

					if (j > 1 && i < 2) {

						while (checkSequenceUp(arrayOfPieces[j][i])) {

							arrayOfPieces[j][i] = null;

							arrayOfPieces[j][i] = createRandomLokum();

							arrayOfPieces[j][i].setPosX(i);

							arrayOfPieces[j][i].setPosY(j);

						}

					}
					if (j < 2 & i > 1) {

						while (checkSequenceLeft(arrayOfPieces[j][i])) {

							arrayOfPieces[j][i] = null;

							arrayOfPieces[j][i] = createRandomLokum();

							arrayOfPieces[j][i].setPosX(i);

							arrayOfPieces[j][i].setPosY(j);

						}

					}
				}

			}
		}

	}

	/**
	 * @requires arrayOfPieces != null
	 * @modifies XML file
	 * @ensures the elements of arrayOfPieces should be written to XML file.
	 */
	public void saveGameBoard() {

		if (arrayOfPieces != null) {

			try {

				DocumentBuilderFactory docFactory = DocumentBuilderFactory
						.newInstance();

				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

				Document doc = docBuilder.newDocument();

				Element rootElement = doc.createElement("game");

				doc.appendChild(rootElement);

				Element player = doc.createElement("player");

				rootElement.appendChild(player);

				Element p_name = doc.createElement("name");

				p_name.appendChild(doc.createTextNode("emre"));

				player.appendChild(p_name);

				getXmlLevel(doc, rootElement);

				getXmlTarget(doc, rootElement);

				getXmlScore(doc, rootElement);

				getXmlMoves(doc, rootElement);

				getXmlArray(doc, rootElement);

				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();

				Transformer transformer = transformerFactory.newTransformer();

				DOMSource source = new DOMSource(doc);

				StreamResult result = new StreamResult(
						new File(
								"/Users/EmreCevik/Documents/workspace(Eclipse)/ChewyLokumLegend/src/xmls/saved.xml"));

				// StreamResult console = new StreamResult (System.out);

				// transformer.transform(source, console);

				transformer.transform(source, result);

				System.out.println("file saved!");
			}

			catch (ParserConfigurationException pce) {

				pce.printStackTrace();

			} catch (TransformerException tfe) {

				tfe.printStackTrace();

			}

		}

	}

	private void getXmlLevel(Document doc, Element rootElement) {
		// TODO Auto-generated method stub

	}

	public void getXmlArray(Document doc, Element element) {

		Element board = doc.createElement("board");

		element.appendChild(board);

		Element lokums = doc.createElement("lokums");

		board.appendChild(lokums);

		for (int i = 0; i < width; i++) {

			for (int j = 0; j < height; j++) {

				Element lokum = doc.createElement("lokum");

				lokums.appendChild(lokum);

				Element type = doc.createElement("type");

				type.appendChild(doc.createTextNode(Integer
						.toString(arrayOfPieces[j][i].getType())));

				lokum.appendChild(type);

				Element position = doc.createElement("position");

				lokum.appendChild(position);

				Element xPos = doc.createElement("xcoord");

				xPos.appendChild(doc.createTextNode(Integer
						.toString(arrayOfPieces[j][i].getPosX())));

				position.appendChild(xPos);

				Element yPos = doc.createElement("ycoord");

				yPos.appendChild(doc.createTextNode(Integer
						.toString(arrayOfPieces[j][i].getPosY())));

				position.appendChild(yPos);

			}

		}

	}

	public void getXmlTarget(Document doc, Element element) {

		String target_val = Integer.toString(gameLevel.getTarget());

		Element target = doc.createElement("target");

		target.appendChild(doc.createTextNode(target_val));

		element.appendChild(target);

	}

	public void getXmlScore(Document doc, Element element) {

		String score_val = Integer.toString(getScore());

		Element score = doc.createElement("score");

		score.appendChild(doc.createTextNode(score_val));

		element.appendChild(score);

	}

	public void getXmlMoves(Document doc, Element element) {

		String move_val = Integer.toString(gameLevel.getNumberOfMovement());

		Element moves = doc.createElement("moves");

		moves.appendChild(doc.createTextNode(move_val));

		element.appendChild(moves);

	}

	/**
	 * 
	 * @requires arrayOfPieces = null
	 * 
	 * @modifies arrayOfPieces
	 * 
	 * @ensures XML file should be read and arrayOfPieces is initialized
	 * 
	 *          according to XML file. After, XML file is cleared.
	 */

	public void loadGameBoard() throws ParserConfigurationException {

		File file = new File(
				"/Users/user/Desktop/comp302/Chewy Lokum Legend/Unit Test/ChewyLokumLegend/src/xmls");

		DocumentBuilder db = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();

		Document doc = null;

		try {

			doc = db.parse(file);

		} catch (SAXException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

		Element root = doc.getDocumentElement();

		root.normalize();

		gameLevel.setCurrentLevel(Integer.parseInt(root
				.getElementsByTagName("level").item(0).getTextContent()));

		gameLevel.setTarget(Integer.parseInt(root
				.getElementsByTagName("target").item(0).getTextContent()));

		setScore(Integer.parseInt(root.getElementsByTagName("score").item(0)
				.getTextContent()));

		gameLevel.updateNumOfMovements(Integer.parseInt(root
				.getElementsByTagName("moves").item(0).getTextContent()));

		NodeList lokums = doc.getElementsByTagName("lokum");

		for (int temp = 0; temp < lokums.getLength(); temp++) {

			Node node = (Node) lokums.item(temp);

			Element lokum = (Element) node;

			node.getAttributes();

			BasicLokum lok = new BasicLokum(Integer.parseInt(root
					.getElementsByTagName("type").item(0).getTextContent()));

			// BasicLokum lok = new
			// BasicLokum(Integer.parseInt(lokum.getElementsByTagName("type").item(0).getTextContent()));

			lok.setPosX(Integer.parseInt(lokum.getElementsByTagName("xcoord")
					.item(0).getTextContent()));

			lok.setPosY(Integer.parseInt(lokum.getElementsByTagName("ycoord")
					.item(0).getTextContent()));

			arrayOfPieces[lok.getPosX()][lok.getPosY()] = lok;

			arrayOfPieces[lok.getPosX()][lok.getPosY()].setPosX(lok.getPosX());

			arrayOfPieces[lok.getPosX()][lok.getPosY()].setPosY(lok.getPosY());

		}

	}

	/**
	 * @throws Exception
	 * @requires Number of movement > 0, some element of arrayOfPieces should be
	 *           null.
	 * @modifies arrayOfPieces
	 * @ensures Existing lokums must fall down and fill the null elements of
	 *          arrayOfPieces. Afterwards randomly lokums must be created
	 *          according to the amount of null elements in arrayOfPieces.
	 *          Created Lokums are placed into arrayOfPieces. Then
	 *          checkSequence()is called for all positions of arrayOfPieces,
	 *          isAnyCombinationLeft() is updated, and also scoreBox is updated.
	 */

	public void updateCrush() {
		System.out.println("update e girdi.");
		for (int i = 0; i < width - 1; i++) {
			for (int j = height - 1; j > 0; j--) {
				if (arrayOfPieces[j][i].getType() == -1) {
					int posy = j;
					while (posy - 1 >= 0
							&& arrayOfPieces[posy - 1][i].getType() == -1) {
						posy--;
					}
					if (posy - 1 >= 0) {
						Piece temp;
						temp = arrayOfPieces[posy - 1][i];
						temp.setPosX(i);
						temp.setPosY(j);
						arrayOfPieces[j][i] = temp;
						Null nullLokum = new Null();
						nullLokum.setPosX(i);
						nullLokum.setPosY(posy - 1);
						arrayOfPieces[posy - 1][i] = nullLokum;
					}
				}
			}
		}
		randomlyFallDown();
	}

	/**
	 * @throws Exception
	 * @requires numOfMovement > 0 , isAnyCombinationLeft() = #false ,
	 *           arrayOfPieces != null
	 * @modifies arrayOfPieces
	 * @ensures isAnyCombinationLeft() = #false
	 */

	public void replaceBoard() {
		if (isAnyCombinationLeft() == false && arrayOfPieces != null
				&& gameLevel.getNumberOfMovement() > 0) {
			Piece[] replaceHelper = new Piece[width * height];
			int counter = 0;
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					arrayOfPieces[j][i] = replaceHelper[counter++];
				}
			}

			Collections.shuffle(Arrays.asList(replaceHelper));

			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					arrayOfPieces[j][i] = replaceHelper[counter--];
				}
			}
		}
	}

	/**
	 * @requires Some element of arrayOfPieces should be null.
	 * @modifies arrayOfPieces
	 * @ensures Randomly lokums must be created according to the amount of null
	 *          elements in arrayOfPieces. Created Lokums are placed into
	 *          arrayOfPieces. After that,scoreBox is updated.
	 */

	public void randomlyFallDown() {
		for (int i = 0; i < width; i++) {
			for (int j = height - 1; j >= 0; j--) {
				if (arrayOfPieces[j][i].getType() == -1) {
					System.out.println("randomlyFallDown içine girdi.");
					Lokum randomLokum = createRandomLokum();
					randomLokum.setPosX(i);
					randomLokum.setPosY(j);
					arrayOfPieces[j][i] = randomLokum;
				}
			}
		}
	}

	// phase 2 de yapýlacak.
	/**
	 * @requires
	 * @modifies
	 * @ensures
	 */
	public void updateStarBar() {

	}

	/**
	 * @requires piece.x >= 0 and piece.x < width, piece.y >= 0 and piece.y <
	 *           height, piece != null
	 * @param piece
	 * @return boolean true if the coordinates are in the specific allowed
	 *         boundary.
	 * @throws Exception
	 */

	public boolean isWithinBoard(Piece piece) {
		boolean within = false;
		if (0 <= piece.getPosX() && piece.getPosX() < width
				&& 0 <= piece.getPosY() && piece.getPosY() < height)
			within = true;
		return within;
	}

	/**
	 * @requires arrayOfPieces != null
	 * @return boolean true if any crush can happen in the board.
	 * @throws Exception
	 */
	public boolean isAnyCombinationLeft() {
		boolean state = false;
		Piece temp;
		if (arrayOfPieces != null) {
			outerLoop: for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					temp = arrayOfPieces[j][i];
					temp.setPosX(i);
					temp.setPosY(j);
					if (isWithinBoard(arrayOfPieces[j - 1][i])) {
						swapPiece(temp, arrayOfPieces[j - 1][i]);
						if (checkSequenceLeft(temp)) { // Deðiþirse eðer solunda
														// sequence olacak mý
														// diye bakýyor.
							state = true;
							swapPiece(temp, arrayOfPieces[j][i]);
							break outerLoop;
						} else if (checkSequenceRight(temp)) { // Deðiþirse
																// saðýnda
																// sequence
																// olacak mý
																// diye bakýyor.
							state = true;
							swapPiece(temp, arrayOfPieces[j][i]);
							break outerLoop;
						} else if (checkSequenceUp(temp)) { // Deðiþirse
															// üsttünde sequence
															// olacak mý diye
															// bakýyor.
							state = true;
							swapPiece(temp, arrayOfPieces[j][i]);
							break outerLoop;
						} else if (checkSequenceHorizontalMiddle(temp)) { // Deðiþirse
																			// ortada
																			// sequence
																			// olacak
																			// mý
																			// diye
																			// bakýyor.
							state = true;
							swapPiece(temp, arrayOfPieces[j][i]);
							break outerLoop;
						} else { // yoksa lokumu eski yerine koyuyor.
							swapPiece(temp, arrayOfPieces[j][i]);
						}

					}
					if (isWithinBoard(arrayOfPieces[j + 1][i])) {
						swapPiece(temp, arrayOfPieces[j + 1][i]);
						if (checkSequenceLeft(temp)) { // Deðiþirse eðer solunda
														// sequence olacak mý
														// diye bakýyor.
							state = true;
							swapPiece(temp, arrayOfPieces[j][i]);
							break outerLoop;
						} else if (checkSequenceRight(temp)) { // Deðiþirse
																// saðýnda
																// sequence
																// olacak mý
																// diye bakýyor.
							state = true;
							swapPiece(temp, arrayOfPieces[j][i]);
							break outerLoop;
						} else if (checkSequenceUp(temp)) { // Deðiþirse
															// üsttünde sequence
															// olacak mý diye
															// bakýyor.
							state = true;
							swapPiece(temp, arrayOfPieces[j][i]);
							break outerLoop;
						} else if (checkSequenceHorizontalMiddle(temp)) { // Deðiþirse
																			// ortada
																			// sequence
																			// olacak
																			// mý
																			// diye
																			// bakýyor.
							state = true;
							swapPiece(temp, arrayOfPieces[j][i]);
							break outerLoop;
						} else { // yoksa lokumu eski yerine koyuyor.
							swapPiece(temp, arrayOfPieces[j][i]);
						}

					}
					if (isWithinBoard(arrayOfPieces[j][i - 1])) {
						swapPiece(temp, arrayOfPieces[j][i - 1]);
						if (checkSequenceLeft(temp)) { // Deðiþirse eðer solunda
														// sequence olacak mý
														// diye bakýyor.
							state = true;
							swapPiece(temp, arrayOfPieces[j][i]);
							break outerLoop;
						} else if (checkSequenceRight(temp)) { // Deðiþirse
																// saðýnda
																// sequence
																// olacak mý
																// diye bakýyor.
							state = true;
							swapPiece(temp, arrayOfPieces[j][i]);
							break outerLoop;
						} else if (checkSequenceUp(temp)) { // Deðiþirse
															// üsttünde sequence
															// olacak mý diye
															// bakýyor.
							state = true;
							swapPiece(temp, arrayOfPieces[j][i]);
							break outerLoop;
						} else if (checkSequenceHorizontalMiddle(temp)) { // Deðiþirse
																			// ortada
																			// sequence
																			// olacak
																			// mý
																			// diye
																			// bakýyor.
							state = true;
							swapPiece(temp, arrayOfPieces[j][i]);
							break outerLoop;
						} else { // yoksa lokumu eski yerine koyuyor.
							swapPiece(temp, arrayOfPieces[j][i]);
						}

					}
					if (isWithinBoard(arrayOfPieces[j][i + 1])) {
						swapPiece(temp, arrayOfPieces[j][i + 1]);
						if (checkSequenceLeft(temp)) { // Deðiþirse eðer solunda
														// sequence olacak mý
														// diye bakýyor.
							state = true;
							swapPiece(temp, arrayOfPieces[j][i]);
							break outerLoop;
						} else if (checkSequenceRight(temp)) { // Deðiþirse
																// saðýnda
																// sequence
																// olacak mý
																// diye bakýyor.
							state = true;
							swapPiece(temp, arrayOfPieces[j][i]);
							break outerLoop;
						} else if (checkSequenceUp(temp)) { // Deðiþirse
															// üsttünde sequence
															// olacak mý diye
															// bakýyor.
							state = true;
							swapPiece(temp, arrayOfPieces[j][i]);
							break outerLoop;
						} else if (checkSequenceHorizontalMiddle(temp)) { // Deðiþirse
																			// ortada
																			// sequence
																			// olacak
																			// mý
																			// diye
																			// bakýyor.
							state = true;
							swapPiece(temp, arrayOfPieces[j][i]);
							break outerLoop;
						} else { // yoksa lokumu eski yerine koyuyor.
							swapPiece(temp, arrayOfPieces[j][i]);
						}

					}
				}
			}
		}
		return state;
	}

	public Level getGameLevel() {
		return gameLevel;
	}

	public void setGameLevel(Level gameLevel) {
		this.gameLevel = gameLevel;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Piece[][] getArrayOfPieces() {
		return arrayOfPieces;
	}

	public void setArrayOfPieces(Piece[][] arrayOfPieces) {
		this.arrayOfPieces = arrayOfPieces;
	}

	public Piece getFirstPressed() {
		return firstPressed;
	}

	public void setFirstPressed(Piece piece) {
		firstPressed = piece;
	}

	public void setSecondPressed(Piece piece) {
		secondPressed = piece;
	}

	public Piece getSecondPressed() {
		return secondPressed;
	}

	/**
	 * @requires Whether (lokum.x + 1), (lokum.x + 2), (lokum.x - 2), (lokum.x -
	 *           1), (lokum.y + 1), (lokum.y + 2), (lokum.y - 1), (lokum.y - 2)
	 *           positions in arrayOfPieces are same as lokum or not is checked.
	 * @param lokum
	 * @return boolean true if there is any crush in the position of lokum.
	 */
	public boolean checkSequenceDown(Piece piece) {

		boolean bool = false;

		// lokumlar isWithinBoard() mu diye kontrol edilecek.
		if (piece.getType() > 0) {

			if (piece.getPosY() + 2 < height) {

				if (piece.getType() == arrayOfPieces[piece.getPosY() + 1][piece

				.getPosX()].getType()

				&& piece.getType() == arrayOfPieces[piece.getPosY() + 2][piece

				.getPosX()].getType())

					bool = true;

				// varsa true.

			}
		}
		return bool;

	}

	// arrayOfPieces[j][i].getType()==arrayOfPieces[j][i-1].getType() &&
	// arrayOfPieces[j][i].getType()==arrayOfPieces[j][i-2].getType())||(arrayOfPieces[j][i].getType()==arrayOfPieces[j-1][i].getType()
	// && arrayOfPieces[j][i].getType()==arrayOfPieces[j-2][i].getType())

	public boolean checkSequenceUp(Piece piece) {

		boolean bool = false;
		if (piece.getType() > 0) {

			if (piece.getPosY() - 2 >= 0) {

				if (piece.getType() == arrayOfPieces[piece.getPosY() - 1][piece

				.getPosX()].getType()

				&& piece.getType() == arrayOfPieces[piece.getPosY() - 2][piece

				.getPosX()].getType())

					bool = true; // lokumun yukarsnda ard arda 2 tane ayn

				// varsa true.

			}
		}
		return bool;

	}

	public boolean checkSequenceRight(Piece piece) {

		boolean bool = false;
		if (piece.getType() > 0) {

			if (piece.getPosX() + 2 < width) {

				if (piece.getType() == arrayOfPieces[piece.getPosY()][piece

				.getPosX() + 1].getType()

				&& piece.getType() == arrayOfPieces[piece.getPosY()][piece

				.getPosX() + 2].getType())

					bool = true;

				// varsa

				// true.

			}
		}
		return bool;

	}

	public boolean checkSequenceLeft(Piece piece) {

		boolean bool = false;

		if (piece.getType() > 0) {
			if (piece.getPosX() - 2 >= 0) {

				if (piece.getType() == arrayOfPieces[piece.getPosY()][piece

				.getPosX() - 1].getType()

				&& piece.getType() == arrayOfPieces[piece.getPosY()][piece

				.getPosX() - 2].getType())

					bool = true; // lokumun solunda ard arda 2 tane ay

				// varsa

				// true.

			}
		}
		return bool;

	}

	public boolean checkSequenceHorizontalMiddle(Piece piece) {

		boolean bool = false;
		if (piece.getType() > 0) {
			if (piece.getPosX() - 1 >= 0 && piece.getPosX() + 1 < width) {

				if (piece.getType() == arrayOfPieces[piece.getPosY()][piece
						.getPosX() + 1]

				.getType()

				&& piece.getType() == arrayOfPieces[piece.getPosY()][piece

				.getPosX() - 1].getType())

					bool = true; // lokumun
			}
			// varsa true.
		}
		return bool;

	}

	public boolean checkSequenceVerticalMiddle(Piece piece) {

		boolean bool = false;
		if (piece.getType() > 0) {
			if (piece.getPosY() - 1 >= 0 && piece.getPosY() + 1 < height) {

				if (piece.getType() == arrayOfPieces[piece.getPosY() + 1][piece

				.getPosX()].getType()

				&& piece.getType() == arrayOfPieces[piece.getPosY() - 1][piece

				.getPosX()].getType())

					bool = true; // lokumun
			}
		}
		return bool;

	}

	/**
	 * @requires checkSequence() = true. Whether sequence of crush is upwards or
	 *           downwards or rightwards or leftwards is controlled.
	 * @param lokum
	 * @modifies arrayOfPieces
	 * @ensures the positions of sequence of crush should be null after
	 *          controlling. Then lokums in the sequence should be disappeared.
	 */

	public void remove(Lokum lokum) {
		int posX = lokum.getPosX();
		int posY = lokum.getPosY();
		int type = lokum.getType();
		if (arrayOfPieces != null) {
			if (checkSequenceUp(lokum))
				upSequence = true;
			System.out.println("upsequence deðeri: " + upSequence);

			if (checkSequenceRight(lokum))
				rightSequence = true;
			System.out.println("rightsequence deðeri: " + rightSequence);

			if (checkSequenceLeft(lokum))
				leftSequence = true;
			System.out.println("leftsequence deðeri: " + leftSequence);

			if (checkSequenceHorizontalMiddle(lokum))
				horizontalMiddleSequence = true;
			System.out.println("horizontalsequence deðeri: "
					+ horizontalMiddleSequence);

			if (checkSequenceVerticalMiddle(lokum))
				verticalMiddleSequence = true;
			System.out.println("verticalsequence deðeri: "
					+ verticalMiddleSequence);

			if (checkSequenceDown(lokum))
				downSequence = true;
			System.out.println("downsequence deðeri: " + downSequence);

			if (upSequence == true && rightSequence == false
					&& verticalMiddleSequence == false && leftSequence == false
					&& horizontalMiddleSequence == false
					&& downSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = 0; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX);
					nullLokum.setPosY(posY - i);
					arrayOfPieces[posY - i][posX] = nullLokum;
				}
				setScore(Score.basicCalculation());
				handleScoreModification();
				upSequence = false;
				return;
			} else if (upSequence == false && rightSequence == true
					&& verticalMiddleSequence == false && leftSequence == false
					&& horizontalMiddleSequence == false
					&& downSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = 0; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX + i);
					nullLokum.setPosY(posY);
					arrayOfPieces[posY][posX + i] = nullLokum;
				}
				setScore(Score.basicCalculation());
				handleScoreModification();
				rightSequence = false;
				return;
			} else if (upSequence == false && rightSequence == false
					&& verticalMiddleSequence == true && leftSequence == false
					&& horizontalMiddleSequence == false
					&& downSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -1; i < 2; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX);
					nullLokum.setPosY(posY + i);
					arrayOfPieces[posY + i][posX] = nullLokum;

				}
				setScore(Score.basicCalculation());
				handleScoreModification();
				verticalMiddleSequence = false;
				return;
			} else if (upSequence == false && rightSequence == false
					&& verticalMiddleSequence == false && leftSequence == true
					&& horizontalMiddleSequence == false
					&& downSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = 0; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX - i);
					nullLokum.setPosY(posY);
					arrayOfPieces[posY][posX - i] = nullLokum;

				}
				setScore(Score.basicCalculation());
				handleScoreModification();
				leftSequence = false;
				return;
			} else if (upSequence == false && rightSequence == false
					&& verticalMiddleSequence == false && leftSequence == false
					&& horizontalMiddleSequence == true
					&& downSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -1; i < 2; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX + i);
					nullLokum.setPosY(posY);
					arrayOfPieces[posY][posX + i] = nullLokum;

				}
				setScore(Score.basicCalculation());
				handleScoreModification();
				horizontalMiddleSequence = false;
				return;
			} else if (upSequence == false && rightSequence == false
					&& verticalMiddleSequence == false && leftSequence == false
					&& horizontalMiddleSequence == false
					&& downSequence == true) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = 0; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX);
					nullLokum.setPosY(posY + i);
					arrayOfPieces[posY + i][posX] = nullLokum;

				}
				setScore(Score.basicCalculation());
				handleScoreModification();
				downSequence = false;
				return;
			}
			// 2 li olasýlýklar
			// Special lokums can occur right after crushes.
			// Codes below create special lokums and remove crushed lokums.

			else if (upSequence == true && rightSequence == true
					&& verticalMiddleSequence == false && downSequence == false
					&& horizontalMiddleSequence == false
					&& leftSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = 0; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX);
					nullLokum.setPosY(posY - i);
					arrayOfPieces[posY - i][posX] = nullLokum;
					if (i > 0) {
						Null nullLokumOther = new Null();
						nullLokumOther.setPosX(posX + i);
						nullLokumOther.setPosY(posY);
						arrayOfPieces[posY][posX + i] = nullLokumOther;
					}
				}

				// create a wrapped in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				WrappedLokum temp = new WrappedLokum(type);
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.wrappedCombinationCalculation());
				handleScoreModification();
				upSequence = false;
				rightSequence = false;
				return;
			} else if (upSequence == true && verticalMiddleSequence == true
					&& horizontalMiddleSequence == false
					&& leftSequence == false && downSequence == false
					&& rightSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -2; i < 2; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX);
					nullLokum.setPosY(posY + i);
					arrayOfPieces[posY + i][posX] = nullLokum;

				}
				// create a horizontal stripped in a given type of input lokum
				// in the
				// arrayOfPieces[posY][posX]
				StrippedLokum temp = new StrippedLokum(type, "horizontal");
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.strippedCombinationCalculation());
				handleScoreModification();
				upSequence = false;
				verticalMiddleSequence = false;
				return;
			} else if (upSequence == true && leftSequence == true
					&& rightSequence == false && downSequence == false
					&& horizontalMiddleSequence == false
					&& verticalMiddleSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = 0; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX);
					nullLokum.setPosY(posY - i);
					arrayOfPieces[posY - i][posX] = nullLokum;
					if (i > 0) {
						Null nullLokumOther = new Null();
						nullLokumOther.setPosX(posX - i);
						nullLokumOther.setPosY(posY);
						arrayOfPieces[posY][posX - i] = nullLokumOther;
					}
				}

				// create a wrapped in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				WrappedLokum temp = new WrappedLokum(type);
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.wrappedCombinationCalculation());
				handleScoreModification();
				upSequence = false;
				leftSequence = false;
				return;
			} else if (upSequence == true && horizontalMiddleSequence == true
					&& downSequence == false && verticalMiddleSequence == false
					&& rightSequence == false && leftSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = 0; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX - 1 + i);
					nullLokum.setPosY(posY);
					arrayOfPieces[posY][posX - 1 + i] = nullLokum;
					if (i > 0) {
						Null nullLokumOther = new Null();
						nullLokumOther.setPosX(posX);
						nullLokumOther.setPosY(posY - i);
						arrayOfPieces[posY - i][posX] = nullLokumOther;
					}

				}

				// create a wrapped in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				WrappedLokum temp = new WrappedLokum(type);
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.wrappedCombinationCalculation());
				handleScoreModification();
				upSequence = false;
				horizontalMiddleSequence = false;
				return;
			} else if (upSequence == true && downSequence == true
					&& rightSequence == false && leftSequence == false
					&& horizontalMiddleSequence == false
					&& verticalMiddleSequence == true) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -2; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX);
					nullLokum.setPosY(posY + i);
					arrayOfPieces[posY + i][posX] = nullLokum;
				}
				// create a color bomb in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				ColorBombLokum temp = new ColorBombLokum();
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.colorBombCombinationCalculation());
				handleScoreModification();
				upSequence = false;
				downSequence = false;
				verticalMiddleSequence = false;
				return;
			} else if (rightSequence == true && verticalMiddleSequence == true
					&& leftSequence == false
					&& horizontalMiddleSequence == false && upSequence == false
					&& downSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = 0; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX);
					nullLokum.setPosY(posY + i - 1);
					arrayOfPieces[posY + i - 1][posX] = nullLokum;
					if (i > 0) {
						Null nullLokum2 = new Null();
						nullLokum2.setPosX(posX + i);
						nullLokum2.setPosY(posY);
						arrayOfPieces[posY][posX + i] = nullLokum2;
					}
				}

				// create a wrapped in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				WrappedLokum temp = new WrappedLokum(type);
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.wrappedCombinationCalculation());
				handleScoreModification();
				rightSequence = false;
				verticalMiddleSequence = false;
				return;
			} else if (rightSequence == true && leftSequence == true
					&& horizontalMiddleSequence == true
					&& verticalMiddleSequence == false && upSequence == false
					&& downSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -2; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX + i);
					nullLokum.setPosY(posY);
					arrayOfPieces[posY][posX + i] = nullLokum;

				}
				// create a color bomb in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				ColorBombLokum temp = new ColorBombLokum();
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.colorBombCombinationCalculation());
				handleScoreModification();
				rightSequence = false;
				leftSequence = false;
				horizontalMiddleSequence = false;
				return;
			} else if (rightSequence == true
					&& horizontalMiddleSequence == true
					&& leftSequence == false && verticalMiddleSequence == false
					&& upSequence == false && downSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -1; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX + i);
					nullLokum.setPosY(posY);
					arrayOfPieces[posY][posX + i] = nullLokum;

				}
				// create a vertical stripped in a given type of input
				// lokum in the arrayOfPieces[posY][posX]
				StrippedLokum temp = new StrippedLokum(type, "vertical");
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.strippedCombinationCalculation());
				handleScoreModification();
				rightSequence = false;
				horizontalMiddleSequence = false;
				return;
			} else if (rightSequence == true && downSequence == true
					&& leftSequence == false && upSequence == false
					&& horizontalMiddleSequence == false
					&& verticalMiddleSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = 0; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX + i);
					nullLokum.setPosY(posY);
					arrayOfPieces[posY][posX + i] = nullLokum;
					if (i > 0) {
						Null nullLokum2 = new Null();
						nullLokum2.setPosX(posX);
						nullLokum2.setPosY(posY + i);
						arrayOfPieces[posY + i][posX] = nullLokum2;
					}

				}

				// create a wrapped in a given type of input
				// lokum in the arrayOfPieces[posY][posX]
				WrappedLokum temp = new WrappedLokum(type);
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.wrappedCombinationCalculation());
				handleScoreModification();
				rightSequence = false;
				downSequence = false;
				return;
			} else if (verticalMiddleSequence == true && leftSequence == true
					&& rightSequence == false
					&& horizontalMiddleSequence == false && upSequence == false
					&& downSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = 0; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX);
					nullLokum.setPosY(posY + i - 1);
					arrayOfPieces[posY + i - 1][posX] = nullLokum;
					if (i > 0) {
						Null nullLokum2 = new Null();
						nullLokum2.setPosX(posX - i);
						nullLokum2.setPosY(posY);
						arrayOfPieces[posY][posX - i] = nullLokum2;
					}

				}
				// create a wrapped in a given type of input
				// lokum in the arrayOfPieces[posY][posX]
				WrappedLokum temp = new WrappedLokum(type);
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.wrappedCombinationCalculation());
				handleScoreModification();
				verticalMiddleSequence = false;
				leftSequence = false;
				return;
			} else if (verticalMiddleSequence == true && downSequence == true
					&& upSequence == false && horizontalMiddleSequence == false
					&& rightSequence == false && leftSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -1; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX);
					nullLokum.setPosY(posY + i);
					arrayOfPieces[posY + i][posX] = nullLokum;

				}
				// create a horizontal in a given type of input
				// lokum in the arrayOfPieces[posY][posX]
				StrippedLokum temp = new StrippedLokum(type, "horizontal");
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.strippedCombinationCalculation());
				handleScoreModification();
				verticalMiddleSequence = false;
				downSequence = false;
				return;
			} else if (leftSequence == true && horizontalMiddleSequence == true
					&& rightSequence == false
					&& verticalMiddleSequence == false && upSequence == false
					&& downSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -2; i < 2; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX + i);
					nullLokum.setPosY(posY);
					arrayOfPieces[posY][posX + i] = nullLokum;

				}
				// create a vertical stripped in a given type of input lokum in
				// the
				// arrayOfPieces[posY][posX]
				StrippedLokum temp = new StrippedLokum(type, "vertical");
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.strippedCombinationCalculation());
				handleScoreModification();
				leftSequence = false;
				horizontalMiddleSequence = false;
				return;
			} else if (leftSequence == true && downSequence == true
					&& verticalMiddleSequence == false
					&& horizontalMiddleSequence == false && upSequence == false
					&& rightSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = 0; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX - i);
					nullLokum.setPosY(posY);
					arrayOfPieces[posY][posX - i] = nullLokum;
					if (i > 0) {
						Null nullLokum2 = new Null();
						nullLokum2.setPosX(posX);
						nullLokum2.setPosY(posY + i);
						arrayOfPieces[posY + i][posX] = nullLokum2;
					}

				}

				// create a wrapped in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				WrappedLokum temp = new WrappedLokum(type);
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.wrappedCombinationCalculation());
				handleScoreModification();
				leftSequence = false;
				downSequence = false;
				return;
			} else if (horizontalMiddleSequence == true && downSequence == true
					&& upSequence == false && verticalMiddleSequence == false
					&& rightSequence == false && leftSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = 0; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX - 1 + i);
					nullLokum.setPosY(posY);
					arrayOfPieces[posY][posX - 1 + i] = nullLokum;
					if (i > 0) {
						Null nullLokum2 = new Null();
						nullLokum2.setPosX(posX);
						nullLokum2.setPosY(posY + i);
						arrayOfPieces[posY + i][posX] = nullLokum2;
					}
				}

				// create a wrapped in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				WrappedLokum temp = new WrappedLokum(type);
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.wrappedCombinationCalculation());
				handleScoreModification();
				horizontalMiddleSequence = false;
				downSequence = false;
				return;
			}
			// 3 lü olasýlýklar
			else if (upSequence == true && rightSequence == true
					&& verticalMiddleSequence == true
					&& horizontalMiddleSequence == false
					&& leftSequence == false && downSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -1; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX);
					nullLokum.setPosY(posY - i);
					arrayOfPieces[posY - i][posX] = nullLokum;
					if (i > 0) {
						Null nullLokum2 = new Null();
						nullLokum2.setPosX(posX + i);
						nullLokum2.setPosY(posY);
						arrayOfPieces[posY][posX + i] = nullLokum2;
					}
				}

				// create a wrapped in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				WrappedLokum temp = new WrappedLokum(type);
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.wrappedCombinationCalculation());
				handleScoreModification();
				upSequence = false;
				rightSequence = false;
				verticalMiddleSequence = false;
				return;
			} else if (upSequence == true && rightSequence == true
					&& leftSequence == true && horizontalMiddleSequence == true
					&& verticalMiddleSequence == false && downSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -2; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX + i);
					nullLokum.setPosY(posY);
					arrayOfPieces[posY][posX + i] = nullLokum;
					if (i < 0) {
						Null nullLokum2 = new Null();
						nullLokum2.setPosX(posX);
						nullLokum2.setPosY(posY + i);
						arrayOfPieces[posY + i][posX] = nullLokum2;
					}
				}

				// create a color bomb in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				ColorBombLokum temp = new ColorBombLokum();
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.colorBombCombinationCalculation());
				handleScoreModification();
				upSequence = false;
				leftSequence = false;
				rightSequence = false;
				horizontalMiddleSequence = false;
				return;
			} else if (upSequence == true && rightSequence == true
					&& horizontalMiddleSequence == true
					&& verticalMiddleSequence == false && downSequence == false
					&& leftSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -1; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX + i);
					nullLokum.setPosY(posY);
					arrayOfPieces[posY][posX + i] = nullLokum;
					if (i > 0) {
						Null nullLokum2 = new Null();
						nullLokum2.setPosX(posX);
						nullLokum2.setPosY(posY - i);
						arrayOfPieces[posY - i][posX] = nullLokum2;
					}
				}
				// create a wrapped in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				WrappedLokum temp = new WrappedLokum(type);
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.wrappedCombinationCalculation());
				handleScoreModification();
				upSequence = false;
				rightSequence = false;
				horizontalMiddleSequence = false;
				return;
			} else if (upSequence == true && rightSequence == true
					&& downSequence == true && verticalMiddleSequence == true
					&& leftSequence == false
					&& horizontalMiddleSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -2; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX);
					nullLokum.setPosY(posY + i);
					arrayOfPieces[posY + i][posX] = nullLokum;
					if (i > 0) {
						Null nullLokum2 = new Null();
						nullLokum2.setPosX(posX + i);
						nullLokum2.setPosY(posY);
						arrayOfPieces[posY][posX + i] = nullLokum2;
					}
				}

				// create a color bomb in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				ColorBombLokum temp = new ColorBombLokum();
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.colorBombCombinationCalculation());
				handleScoreModification();
				upSequence = false;
				rightSequence = false;
				downSequence = false;
				verticalMiddleSequence = false;
				return;
			} else if (upSequence == true && verticalMiddleSequence == true
					&& leftSequence == true && rightSequence == false
					&& downSequence == false
					&& horizontalMiddleSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -2; i < 2; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX);
					nullLokum.setPosY(posY + i);
					arrayOfPieces[posY + i][posX] = nullLokum;
					if (i < 0) {
						Null nullLokum2 = new Null();
						nullLokum2.setPosX(posX + i);
						nullLokum2.setPosY(posY);
						arrayOfPieces[posY][posX + i] = nullLokum2;
					}
				}

				// create a wrapped in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				WrappedLokum temp = new WrappedLokum(type);
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.wrappedCombinationCalculation());
				handleScoreModification();
				upSequence = false;
				verticalMiddleSequence = false;
				leftSequence = false;
				return;
			} else if (upSequence == true && leftSequence == true
					&& horizontalMiddleSequence == true
					&& downSequence == false && rightSequence == true
					&& verticalMiddleSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -2; i < 2; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX + i);
					nullLokum.setPosY(posY);
					arrayOfPieces[posY][posX + i] = nullLokum;
					if (i < 0) {
						Null nullLokum2 = new Null();
						nullLokum2.setPosX(posX);
						nullLokum2.setPosY(posY + i);
						arrayOfPieces[posY + i][posX] = nullLokum2;
					}
				}

				// create a wrapped in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				WrappedLokum temp = new WrappedLokum(type);
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.wrappedCombinationCalculation());
				handleScoreModification();
				upSequence = false;
				leftSequence = false;
				horizontalMiddleSequence = false;
				return;
			} else if (upSequence == true && leftSequence == true
					&& downSequence == true && verticalMiddleSequence == true
					&& rightSequence == false
					&& horizontalMiddleSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -2; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX);
					nullLokum.setPosY(posY + i);
					arrayOfPieces[posY + i][posX] = nullLokum;
					if (i < 0) {
						Null nullLokum2 = new Null();
						nullLokum2.setPosX(posX + i);
						nullLokum2.setPosY(posY);
						arrayOfPieces[posY][posX + i] = nullLokum2;
					}
				}

				// create a color bomb in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				ColorBombLokum temp = new ColorBombLokum();
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.colorBombCombinationCalculation());
				handleScoreModification();
				upSequence = false;
				leftSequence = false;
				downSequence = false;
				verticalMiddleSequence = false;
				return;
			} else if (rightSequence == true && verticalMiddleSequence == true
					&& downSequence == true) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -1; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX);
					nullLokum.setPosY(posY + i);
					arrayOfPieces[posY + i][posX] = nullLokum;
					if (i > 0) {
						Null nullLokum2 = new Null();
						nullLokum2.setPosX(posX + i);
						nullLokum2.setPosY(posY);
						arrayOfPieces[posY][posX + i] = nullLokum2;
					}
				}

				// create a wrapped in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				WrappedLokum temp = new WrappedLokum(type);
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.wrappedCombinationCalculation());
				handleScoreModification();
				rightSequence = false;
				verticalMiddleSequence = false;
				downSequence = false;
				return;
			} else if (rightSequence == true && leftSequence == true
					&& downSequence == true && horizontalMiddleSequence == true
					&& verticalMiddleSequence == false && upSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -2; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX + i);
					nullLokum.setPosY(posY);
					arrayOfPieces[posY][posX + i] = nullLokum;
					if (i > 0) {
						Null nullLokum2 = new Null();
						nullLokum2.setPosX(posX);
						nullLokum2.setPosY(posY + i);
						arrayOfPieces[posY + i][posX] = nullLokum2;
					}
				}

				// create a color bomb in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				ColorBombLokum temp = new ColorBombLokum();
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.colorBombCombinationCalculation());
				handleScoreModification();
				rightSequence = false;
				leftSequence = false;
				downSequence = false;
				horizontalMiddleSequence = false;
				return;
			} else if (rightSequence == true
					&& horizontalMiddleSequence == true && downSequence == true
					&& leftSequence == false && upSequence == false
					&& verticalMiddleSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -1; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX + i);
					nullLokum.setPosY(posY);
					arrayOfPieces[posY][posX + i] = nullLokum;
					if (i > 0) {
						Null nullLokum2 = new Null();
						nullLokum2.setPosX(posX);
						nullLokum2.setPosY(posY + i);
						arrayOfPieces[posY + i][posX] = nullLokum2;
					}
				}

				// create a wrapped in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				WrappedLokum temp = new WrappedLokum(type);
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.wrappedCombinationCalculation());
				handleScoreModification();
				rightSequence = false;
				horizontalMiddleSequence = false;
				downSequence = false;
				return;
			} else if (leftSequence == true && horizontalMiddleSequence == true
					&& downSequence == true && rightSequence == false
					&& verticalMiddleSequence == false && upSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -2; i < 2; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX + i);
					nullLokum.setPosY(posY);
					arrayOfPieces[posY][posX + i] = nullLokum;
					if (i < 0) {
						Null nullLokum2 = new Null();
						nullLokum2.setPosX(posX);
						nullLokum2.setPosY(posY - i);
						arrayOfPieces[posY - i][posX] = nullLokum2;

					}
				}

				// create a wrapped in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				WrappedLokum temp = new WrappedLokum(type);
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.wrappedCombinationCalculation());
				handleScoreModification();
				leftSequence = false;
				horizontalMiddleSequence = false;
				downSequence = false;
				return;
			} else if (leftSequence == true && verticalMiddleSequence == true
					&& downSequence == true && rightSequence == false
					&& horizontalMiddleSequence == false && upSequence == false) {
				// swap edildikten sonra kontrol edip siliyoruz.
				for (int i = -1; i < 3; i++) {
					Null nullLokum = new Null();
					nullLokum.setPosX(posX);
					nullLokum.setPosY(posY + i);
					arrayOfPieces[posY + i][posX] = nullLokum;
					if (i > 0) {
						Null nullLokum2 = new Null();
						nullLokum2.setPosX(posX - i);
						nullLokum2.setPosY(posY);
						arrayOfPieces[posY][posX - i] = nullLokum2;
					}
				}

				// create a wrapped in a given type of input lokum in the
				// arrayOfPieces[posY][posX]
				WrappedLokum temp = new WrappedLokum(type);
				temp.setPosX(posX);
				temp.setPosY(posY);
				arrayOfPieces[posY][posX] = temp;
				setScore(Score.wrappedCombinationCalculation());
				handleScoreModification();
				leftSequence = false;
				verticalMiddleSequence = false;
				downSequence = false;
				return;
			}
		}
	}

	@SuppressWarnings("unused")
	public void removeRange(Lokum lokum, int range) throws Exception {
		int X = lokum.getPosX();
		int Y = lokum.getPosY();
		Lokum temp = lokum;
		if (lokum == null) {
			throw NullPointerException;
		} else if (range != 1 && range != 2) {
			// throw exception about can not remove the range.
		} else if (range == 1) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (isWithinBoard(arrayOfPieces[Y - 1 + i][X - 1 + j])) {
						arrayOfPieces[Y - 1 + j][X - 1 + i] = null;

					}
				}
			}
			arrayOfPieces[Y + 1][X] = temp;
			arrayOfPieces[Y + 1][X].setPosX(X);
			arrayOfPieces[Y + 1][X].setPosY(Y + 1);
		} else if (range == 2) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (isWithinBoard(arrayOfPieces[Y - 2 + i][X - 2 + j])) {
						arrayOfPieces[Y - 2 + j][X - 2 + i] = null;

					}
				}
			}
			arrayOfPieces[Y + 2][X] = temp;
			arrayOfPieces[Y + 2][X].setPosX(X);
			arrayOfPieces[Y + 2][X].setPosY(Y + 2);
			;
		}
	}

	@SuppressWarnings("unused")
	public int removedByUsingColorBomb(Lokum lokum, Lokum colorBomb)
			throws Exception {
		int type = lokum.getType();
		int counter = 0;
		if (lokum == null) {
			throw NullPointerException;
		} else {
			arrayOfPieces[colorBomb.getPosY()][colorBomb.getPosX()] = null;
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if (arrayOfPieces[j][i].getType() == type)
						counter++;
					arrayOfPieces[j][i] = null;
				}
			}
		}
		return counter;
	}

	/**
	 * @requires checkSequence = true.
	 * @param lokum
	 * @throws Exception
	 * @modifies arrayOfPieces
	 * @ensures the whole elements in arrayOfPieces[lokum.y] should be null and
	 *          the whole piece in the horizontal line should be disappeared.
	 */

	public void removeRow(Lokum lokum) throws Exception {
		if (lokum == null) {
			throw NullPointerException;
		} else {
			for (int i = 0; i < width; i++) {
				arrayOfPieces[lokum.getPosY()][i] = null;
			}
		}
	}

	/**
	 * @requires checkSequence = true.
	 * @param lokum
	 * @throws Exception
	 * @modifies arrayOfPieces
	 * @ensures the whole elements in arrayOfPieces[lokum.x] should be null and
	 *          the whole piece in the vertical line should be disappeared.
	 */

	public void removeColumn(Lokum lokum) throws Exception {
		if (lokum == null) {
			throw NullPointerException;
		} else {
			for (int i = 0; i < height; i++) {
				arrayOfPieces[i][lokum.getPosX()] = null;
			}
		}
	}

	/**
	 * @throws Exception
	 * @requires arrayOfPieces != null
	 * @modifies arrayOfPieces
	 * @ensures the whole element in the arrayOfPieces should be null. And the
	 *          whole piece should be disappeared. Then, exit the game.
	 */
	public void clear() throws Exception {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				arrayOfPieces[i][j] = null;
			}
		}
	}

	public void swapPiece(Piece piece1, Piece piece2) {
		Piece temp, temp2;
		// printPieces();
		int p1X = piece1.getPosX();
		int p1Y = piece1.getPosY();
		int p2X = piece2.getPosX();
		int p2Y = piece2.getPosY();

		temp = arrayOfPieces[piece1.getPosY()][piece1.getPosX()];
		temp2 = arrayOfPieces[piece2.getPosY()][piece2.getPosX()];
		temp.setPosX(p2X);
		temp.setPosY(p2Y);
		temp2.setPosX(p1X);
		temp2.setPosY(p1Y);

		arrayOfPieces[p1Y][p1X] = arrayOfPieces[p2Y][p2X];
		arrayOfPieces[p2Y][p2X] = temp;
		// System.out.println("After Swap:");
		// printPieces();
	}

	public void printPieces() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				System.out.print(arrayOfPieces[i][j].getType());
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	/**
	 * 
	 * @return a Lokum object.
	 * @ensures Created lokum should be initialized.
	 */

	public BasicLokum createRandomLokum() {
		Random rand = new Random();
		int n = rand.nextInt(4) + 1;

		return new BasicLokum(n);
	}

	/**
	 * 
	 * @return a Obstacle object.
	 * @ensures Created obstacle should be initialized.
	 */
	public Obstacle createRandomObstacle() {
		return new Obstacle();
	}

	public void removeBoardSequences() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				remove((Lokum) arrayOfPieces[j][i]);
				updateCrush();
				Score.basicCalculation();
				handleScoreModification();
			}
		}
	}

	public boolean checkBoard() {
		boolean state = false;
		outerLoop: for (int i = 0; i < width - 2; i++) {
			for (int j = 0; j < height - 2; j++) {
				if (checkSequenceDown(arrayOfPieces[j][i])
						|| checkSequenceRight(arrayOfPieces[j][i])) {
					state = true;
					break outerLoop;
				}
			}
		}
		return state;
	}

	public GameWindow getWindow() {
		return window;
	}

	public static void setWindow(GameWindow window) {
		Board.window = window;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score += score;
	}

	public class GameWindow extends JPanel implements MouseListener {
		private BufferedImage image;
		private int numClick;

		public GameWindow() {
			addMouseListener(this);
			numClick = 0;
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					Lokum temp = (Lokum) arrayOfPieces[j][i];
					try {
						image = ImageIO.read(getClass().getResourceAsStream(
								temp.getSourceString()));
					} catch (IOException e) {
						e.printStackTrace();
					}
					g.drawImage(image, temp.getPosX() * 50,
							temp.getPosY() * 50, 50, 50, null);
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {

			// TODO Auto-generated method stub

			numClick++;

			if (numClick % 2 == 1) {

				firstPressed = arrayOfPieces[(int) e.getY() / 50][(int) e
						.getX() / 50];
				System.out.println("1type:" + firstPressed.getType()
						+ "---1xcoord:" + firstPressed.getPosX()
						+ "---1ycoord:" + firstPressed.getPosY() + " NUM-CLICK"
						+ numClick);

			}

			if (numClick % 2 == 0) {

				secondPressed = arrayOfPieces[(int) e.getY() / 50][(int) e
						.getX() / 50];
				System.out.println("2type:" + secondPressed.getType()
						+ "---2xcoord:" + secondPressed.getPosX()
						+ "---2ycoord:" + secondPressed.getPosY()
						+ " NUM-CLICK" + numClick);
				swapPiece(firstPressed, secondPressed);
				revalidate();
				repaint();
				if (firstPressed.getType() == 5 || secondPressed.getType() == 5) {
					specialCrush.crushByColorBomb(firstPressed, secondPressed);
					gameLevel.setNumberOfMovement();
					gameLevel.handleMovementModification();
					updateCrush();
					revalidate();
					repaint();
					System.out.println(getScore());
					return;
				} else if (((firstPressed instanceof StrippedLokum) && (secondPressed instanceof WrappedLokum))
						|| ((secondPressed instanceof StrippedLokum) && (firstPressed instanceof WrappedLokum))) {
					specialCrush.crushByStrippedAndWrapped(firstPressed,
							secondPressed);
					gameLevel.setNumberOfMovement();
					gameLevel.handleMovementModification();
					updateCrush();
					revalidate();
					repaint();
					System.out.println(getScore());
					return;
				} else if (((firstPressed instanceof StrippedLokum) && (secondPressed instanceof BasicLokum))
						|| ((firstPressed instanceof BasicLokum) && (secondPressed instanceof StrippedLokum))) {
					specialCrush.crushByStripped(firstPressed, secondPressed);
					gameLevel.setNumberOfMovement();
					gameLevel.handleMovementModification();
					updateCrush();
					revalidate();
					repaint();
					System.out.println(getScore());
					return;
				} else if (((firstPressed instanceof WrappedLokum) && (secondPressed instanceof BasicLokum))
						|| ((firstPressed instanceof BasicLokum) && (secondPressed instanceof WrappedLokum))) {
					specialCrush.crushByWrapped(firstPressed, secondPressed);
					gameLevel.setNumberOfMovement();
					gameLevel.handleMovementModification();
					updateCrush();
					revalidate();
					repaint();
					System.out.println(getScore());
					return;
				} else if (((firstPressed instanceof WrappedLokum) && (secondPressed instanceof WrappedLokum))) {
					specialCrush.crushByDoubleWrapped(firstPressed,
							secondPressed);
					gameLevel.setNumberOfMovement();
					gameLevel.handleMovementModification();
					updateCrush();
					revalidate();
					repaint();
					System.out.println(getScore());
					return;
				} else if (((firstPressed instanceof StrippedLokum) && (secondPressed instanceof StrippedLokum))) {
					specialCrush.crushByDoubleStripped(firstPressed,
							secondPressed);
					gameLevel.setNumberOfMovement();
					gameLevel.handleMovementModification();
					updateCrush();
					revalidate();
					repaint();
					System.out.println(getScore());
					return;
				} else if (checkSequenceDown(firstPressed)
						|| checkSequenceUp(firstPressed)
						|| checkSequenceHorizontalMiddle(firstPressed)
						|| checkSequenceVerticalMiddle(firstPressed)
						|| checkSequenceLeft(firstPressed)
						|| checkSequenceRight(firstPressed)) {
					// printPieces();
					Board.this.remove((Lokum) firstPressed);
					gameLevel.setNumberOfMovement();
					gameLevel.handleMovementModification();
					System.out.println("After Remove:");
					// printPieces();
					updateCrush();
					revalidate();
					repaint();
					System.out.println(getScore());
					return;
					// Board.updateCrush() buraya gelecek.
				}
				/*
				 * if (checkSequenceDown(secondPressed) ||
				 * checkSequenceUp(secondPressed) ||
				 * checkSequenceHorizontalMiddle(secondPressed) ||
				 * checkSequenceVerticalMiddle(secondPressed) ||
				 * checkSequenceLeft(secondPressed) ||
				 * checkSequenceRight(secondPressed)) {
				 * Board.this.remove((Lokum) secondPressed); //
				 * Board.updateCrush() buraya gelecek. }
				 */else {
					swapPiece(firstPressed, secondPressed);
					revalidate();
					repaint();
					System.out.println(getScore());
				}
			}

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

}
