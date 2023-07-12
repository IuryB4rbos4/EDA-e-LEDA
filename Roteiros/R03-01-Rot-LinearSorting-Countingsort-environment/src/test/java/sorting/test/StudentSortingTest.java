package sorting.test;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import sorting.AbstractSorting;
import sorting.linearSorting.CountingSort;
import sorting.linearSorting.ExtendedCountingSort;

public class StudentSortingTest {

	private Integer[] vetorTamPar;
	private Integer[] vetorTamImpar;
	private Integer[] vetorVazio = {};
	private Integer[] vetorValoresRepetidos;
	private Integer[] vetorValoresIguais;
	private Integer[] populaVetorZeroComecoEFim;
	private Integer[] valoresNegativos;
	private Integer[] valoresNegativos2;
	private Integer[] valoresNegativos3;
	private Integer[] valoresNegativos4;




	public AbstractSorting<Integer> implementation;

	@Before
	public void setUp() {
		populaVetorTamanhoPar(new Integer[] { 30, 28, 7, 29, 11, 26, 4, 22, 23,
				31 });
		populaVetorTamanhoImpar(new Integer[] { 6, 41, 32, 7, 26, 4, 37, 49,
				11, 18, 36 });
		populaVetorRepetido(new Integer[] { 4, 9, 3, 4, 0, 5, 1, 4 });
		populaVetorIgual(new Integer[] { 6, 6, 6, 6, 6, 6 });

		populaVetorZeroComecoEFim(new Integer[] { 0, 4, 9, 5, 0, 3, 4, 0 });

		valoresNegativos(new Integer[] { 4, 9, 3, 4, 0, 5, 1, -4});
		valoresNegativos2(new Integer[] { 4, 9, -3, 4, 0, 5, 1, -4});

		valoresNegativos3(new Integer[] { -4, -9, 3, 4, 0, 5, -1, -4});

		valoresNegativos4(new Integer[] { -4, -9, -3, -4, 0, -5, -1, -4});




		getImplementation();
	}

	// // MÉTODOS AUXILIARES DA INICIALIZAÇÃO
	/**
	 * Método que inicializa a implementação a ser testada com a implementação
	 * do aluno
	 */
	private void getImplementation() {
		// TODO O aluno deve instanciar sua implementação abaixo ao invés de
		// null
		this.implementation = new ExtendedCountingSort();
		
	}

	public void populaVetorTamanhoPar(Integer[] arrayPadrao) {
		this.vetorTamPar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorTamanhoImpar(Integer[] arrayPadrao) {
		this.vetorTamImpar = Arrays.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorRepetido(Integer[] arrayPadrao) {
		this.vetorValoresRepetidos = Arrays.copyOf(arrayPadrao,
				arrayPadrao.length);
	}

	public void populaVetorIgual(Integer[] arrayPadrao) {
		this.vetorValoresIguais = Arrays
				.copyOf(arrayPadrao, arrayPadrao.length);
	}

	public void populaVetorZeroComecoEFim(Integer[] arrayPadrao) {
		this.populaVetorZeroComecoEFim = Arrays.copyOf(arrayPadrao,
				arrayPadrao.length);
	}

	public void valoresNegativos(Integer[] arrayPadrao) {
		this.valoresNegativos = Arrays.copyOf(arrayPadrao,
				arrayPadrao.length);
	}

	public void valoresNegativos2(Integer[] arrayPadrao) {
		this.valoresNegativos2 = Arrays.copyOf(arrayPadrao,
				arrayPadrao.length);
	}

	public void valoresNegativos3(Integer[] arrayPadrao) {
		this.valoresNegativos3 = Arrays.copyOf(arrayPadrao,
				arrayPadrao.length);
	}

	public void valoresNegativos4(Integer[] arrayPadrao) {
		this.valoresNegativos4 = Arrays.copyOf(arrayPadrao,
				arrayPadrao.length);
	}

	// FIM DOS METODOS AUXILIARES DA INICIALIZAÇÃO

	// MÉTODOS DE TESTE

	public void genericTest(Integer[] array) {
		Integer[] copy1 = {};
		if(array.length > 0){
			copy1 = Arrays.copyOf(array, array.length);			
		}
		implementation.sort(array);
		Arrays.sort(copy1);
		Assert.assertArrayEquals(copy1, array);
	}

	@Test
	public void testSort01() {
		genericTest(vetorTamPar);
	}

	@Test
	public void testSort02() {
		genericTest(vetorTamImpar);
	}

	@Test
	public void testSort03() {
		genericTest(vetorVazio);
	}

	@Test
	public void testSort04() {
		genericTest(vetorValoresIguais);
	}

	@Test
	public void testSort05() {
		genericTest(vetorValoresRepetidos);
	}

	@Test
	public void testZerocomeco() {
		genericTest(populaVetorZeroComecoEFim);
	}
	@Test
	public void valoresNegativos() {
		genericTest(valoresNegativos);
	}
	@Test
	public void valoresNegativos2() {
		genericTest(valoresNegativos2);
	}
	@Test
	public void valoresNegativos3() {
		genericTest(valoresNegativos3);
	}
	@Test
	public void valoresNegativos4() {
		genericTest(valoresNegativos4);
	}

	// MÉTODOS QUE OS ALUNOS PODEM CRIAR
	/**
	 * O ALUNO PODE IMPLEMENTAR METODOS DE ORDENAÇÃO TESTANDO O SORT COM TRES
	 * ARGUMENTOS PARA TESTAR A ORDENACAO EM UM PEDAÇO DO ARRAY. DICA: PROCUREM
	 * SEGUIR A ESTRUTURA DOS MÉTODOS DE TESTE ACIMA DESCRITOS, ORDENANDO APENAS
	 * UMA PARTE DO ARRAY.
	 */
}