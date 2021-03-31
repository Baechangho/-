package xml;

class Tf_idf {
    private int tf = 0;
    private double idf = 0;

    public void addTf() {
        this.tf = tf + 1;
    }

    public void setIdf(double idf) {
        this.idf = idf;
    }

    public double getTf_idf() {
        return tf * idf;
    }

    @Override
    public String toString() {
        return String.format("tf = %d | idf = %.2f | tf-idf = %.2f", tf, idf, getTf_idf());
    }
}